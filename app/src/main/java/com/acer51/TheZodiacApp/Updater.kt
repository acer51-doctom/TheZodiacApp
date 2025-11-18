package com.acer51.TheZodiacApp

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.http.HttpResponseCache
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class GitHubRelease(
    @SerialName("tag_name") val tagName: String,
    @SerialName("name") val name: String,
    @SerialName("body") val body: String?,
    @SerialName("assets") val assets: List<Asset>
)

@Serializable
data class Asset(
    @SerialName("browser_download_url") val downloadUrl: String,
    @SerialName("name") val name: String
)

class AppUpdater(private val context: Context) {
    // IMPORTANT: Replace with your GitHub username and repository name
    private val GITHUB_OWNER = "Acer-51"
    private val GITHUB_REPO = "TheZodiacApp"

    private val _updateState = MutableStateFlow<GitHubRelease?>(null)
    val updateState = _updateState.asStateFlow()

    private val httpClient =
        com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient(CIO) {
            HttpResponseCache.install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true; isLenient = true })
        }
    }

    suspend fun checkForUpdates() {
        try {
            val latestRelease: GitHubRelease = httpClient.get("https://api.github.com/repos/$GITHUB_OWNER/$GITHUB_REPO/releases/latest").body()
            val currentVersion = BuildConfig.VERSION_NAME
            // Simple version comparison, assumes formats like "v1.1.0" or "1.1.0"
            if (latestRelease.tagName.removePrefix("v") > currentVersion.removePrefix("v")) {
                _updateState.value = latestRelease
            } else {
                Log.d("AppUpdater", "App is up to date.")
            }
        } catch (e: Exception) {
            Log.e("AppUpdater", "Failed to check for updates", e)
            _updateState.value = null
        }
    }

    fun downloadAndInstall(release: GitHubRelease) {
        val apkAsset = release.assets.find { it.name.endsWith(".apk") }
        if (apkAsset == null) {
            Toast.makeText(context, "No APK found in the release.", Toast.LENGTH_LONG).show()
            return
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(apkAsset.downloadUrl))
            .setTitle(release.name)
            .setDescription("Downloading update...")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, apkAsset.name)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadId = downloadManager.enqueue(request)

        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(ctxt: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    val downloadedFileUri = downloadManager.getUriForDownloadedFile(downloadId)
                    if (downloadedFileUri != null) {
                        installApk(downloadedFileUri)
                    } else {
                        Log.e("AppUpdater", "Downloaded file URI is null.")
                    }
                }
                context.unregisterReceiver(this)
            }
        }

        val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(onComplete, intentFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(onComplete, intentFilter)
        }
    }

    private fun installApk(downloadedFileUri: Uri) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), downloadedFileUri.lastPathSegment!!)
        if (!file.exists()) {
            Log.e("AppUpdater", "Downloaded file does not exist at path: ${file.absolutePath}")
            return
        }

        val contentUri = FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )

        val installIntent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(contentUri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(installIntent)
    }

    fun dismissUpdate() {
        _updateState.value = null
    }
}
# TRANSLATE.md

If you wanna help translating, you will need git and Android Studio.

## Download part.
1. Download Android Studio IDE.
2. Install git.
3. Clone this repository and cd into it.
3.1. Make a new branch following this scheme: `language/<language_REGION>`. Don't put any region code for international.

## On-machine part.
1. Create a folder in `app/res/` and name it `values-<country_code>`. Create a file named `strings.xml` in it.
1.1. OR, you can just right click the res folder, New > Android Resource Directory
1.2. In the left column, choose "Locale"
1.3. Click the 2 arrows button that points to the **right**.
1.4. Select the language.
1.5. Select the region. (e.g: German)
1.6. Go into the folder created. It usually follows this scheme: `values-<country-code>`
1.7. Open the file named `strings.xml`
2. Using the following template (just copy and paste into the file), translate into it. You can also use the language editor (basically a GUI front end if you figure it out).

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name"></string>
    <string name="title_app"></string>

    <string name="tropical_sign_result"></string>
    <string name="sidereal_sign_result"></string>
    <string name="rising_sign_result"></string>
    <string name="current_birthdate"></string>
    <string name="current_birthtime"></string>
    <string name="button_select_birthdate"></string>
    <string name="button_select_birthtime"></string>
    <string name="button_learn_more"></string>
    <string name="button_zodiac_list"></string>
    <string name="disclaimer"></string>

    <string name="dialog_ok"></string>
    <string name="dialog_cancel"></string>

    <string name="popup_detailed_info_title"></string>

    <string name="tropical_vs_sidereal_header"></string>
    <string name="tropical_explanation"></string>
    <string name="sidereal_explanation"></string>
    <string name="what_is_a_rising_sign_header"></string>
    <string name="rising_sign_explanation"></string>
    <string name="your_tropical_sign_label"></string>
    <string name="your_sidereal_sign_label"></string>
    <string name="your_rising_sign_label"></string>
    <string name="no_description_available"></string>

    <string name="popup_info_p3"></string>

    <string name="popup_show_all_zodiacs"></string>
    <string name="popup_list_title"></string>
    <string name="popup_close"></string>

    <string name="aries_name"></string>
    <string name="taurus_name"></string>
    <string name="gemini_name"></string>
    <string name="cancer_name"></string>
    <string name="leo_name"></string>
    <string name="virgo_name"></string>
    <string name="libra_name"></string>
    <string name="scorpio_name"></string>
    <string name="sagittarius_name"></string>
    <string name="capricorn_name"></string>
    <string name="aquarius_name"></string>
    <string name="pisces_name"></string>
    <string name="unknown_sign_name"></string>

    <string name="aries_description"></string>
    <string name="taurus_description"></string>
    <string name="gemini_description"></string>
    <string name="cancer_description"></string>
    <string name="leo_description"></string>
    <string name="virgo_description"></string>
    <string name="libra_description"></string>
    <string name="scorpio_description"></string>
    <string name="sagittarius_description"></string>
    <string name="capricorn_description"></string>
    <string name="aquarius_description"></string>
    <string name="pisces_description"></string>
    <string name="drawer_zodiac_calculator"></string>
    <string name="drawer_settings"></string>

    <string name="settings_title"></string>
    <string name="settings_placeholder_text"></string>

    <string name="shell_menu_content_description"></string>
</resources>
```

Base yourself off of the English translations to translate.
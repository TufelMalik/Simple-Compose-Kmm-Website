This is a Kotlin Multiplatform project targeting Web.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack
channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them
on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle
task.

# To Run the Website Add below gradle task:
`wasmJsBrowserRun -t --quiet --debug`

# To Build the project:
`./gradlew build`


# To publish website on server:
**STEP-1: Build the project**

**STEP-2: In ComposeApp folder /build/dist/ folder will be created**

**STEP-3: Publish all the content of /build/dist/ folder to server.**

**STEP-4: On Server Create or Add ".htaccess" file and add below content in it:**

     AddType application/wasm .wasm
     AddType application/javascript .js .mjs
     AddType text/css .css


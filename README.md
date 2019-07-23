# SheenValidator

Android library to make form validation easier, it is simple and lightweight library 100% write in Kotlin

![alt text](https://i.pinimg.com/originals/1b/ef/f1/1beff152fc029b96028374dd2ab69010.png)

## Installation

```bash
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
	}
}
```
Add depedency
```bash
dependencies {
	implementation 'com.github.rizmaulana:SheenValidator:0.1.1'
}
```
## Validation Support
1. Required / Not Empty
2. Email
3. Phone
4. Website

## Usage
### General Usage

```kotlin
    private lateinit var sheenValidator: SheenValidator
    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        sheenValidator = SheenValidator(this)
        sheenValidator.setOnValidatorListener {
            Toast.makeText(this@SheenValidatorActivity, "Registration form is valid, good job!", Toast.LENGTH_SHORT)
                .show()
        }
        sheenValidator.registerAsRequired(txt_email)
        sheenValidator.registerAsEmail(txt_email)
        sheenValidator.registerAsRequired(txt_phone)
        sheenValidator.registerAsPhone(txt_phone)
        sheenValidator.registerAsRequired(txt_website)
        sheenValidator.registerAsWebsite(txt_website)

        btn_register.setOnClickListener {
            sheenValidator.validate()
        }
    }
```
You can register validation on any view which extends **TextView component**. SheenValidator has default error message like Email is required, email is not valid, etc if you dont use errorListener, but if you want to custom error message you can do it like this ...

### Usage with Error Listener to custom Error

```kotlin
sheenValidator.setOnErrorValidatorListener { list ->
    list.forEach {
         when(it.id){
             R.id.txt_username -> til_username.error = "Oops!... looks like your username is not valid"
             R.id.txt_password -> til_password.error = "Hmm... password is required"
                }
            }
        }
```

### Usage with Annotation
```kotlin 
   lateinit var sheenValidator: SheenValidator

   @RegisterValidation(ValidationType.REQUIRED, 
   ValidationType.EMAIL)
   lateinit var txtUsername: TextInputEditText
   @RegisterValidation(ValidationType.REQUIRED)
   lateinit var txtPassword: TextInputEditText
   ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ...
        txtUsername = findViewById(R.id.txt_email)
        txtPassword = findViewById(R.id.txt_password)

        sheenValidator = SheenValidator(this)
        sheenValidator.setOnValidatorListener {
            til_email.error = ""
            til_password.error = ""
            Toast.makeText(this@SheenValidatorAnnotationActivity, "Your form is valid, good job!", Toast.LENGTH_SHORT)
                .show()
        }
        ValidationBinder.bindValidator(sheenValidator, this)
        btn_login.setOnClickListener { sheenValidator.validate() }

 }

```

## Contributing
Pull requests are welcome. we need more contributor for **validation** rule and default error language, in this version **only support English** as default error message

## License
```bash
   Copyright (C) 2019 rizmaulana@live.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

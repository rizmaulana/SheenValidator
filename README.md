# SheenValidator

[![](https://jitpack.io/v/rizmaulana/SheenValidator.svg)](https://jitpack.io/#rizmaulana/SheenValidator)
![Downloads](https://jitpack.io/v/USER/PROJECT/month.svg)

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
	implementation 'com.github.rizmaulana:SheenValidator:0.2.3'
}
```
## Validation Support
1. Required / Not Empty
2. Email
3. Phone
4. Website
5. Max Char Length
6. Min Char Length

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
        sheenValidator.registerHasMin(txt_website, 5)

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
   MIT License

Copyright (c) 2019 rizmaulana

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

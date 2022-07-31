
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.imablanco/imageprovider/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.imablanco/imageprovider)

# ImageProvider
The easiest way to gather images from camera, gallery, etc.
Retrieve images from camera or gallery without all the boilerplate required (declaring FileProviders, Activtiy result management, bitmap conversion, etc.)

## Installation

ImageProvider is available in the Maven Central, so you just need to add it as a dependency
```groovy
implementation 'io.github.imablanco:imageprovider:{latest version}'
```

## Usage 

Using ImageProvider is super simple, just choose the source to pick the image from and the library will do all the stuff behind the scenes for you

Pick images from Camera:

```kotlin
ImageProvider(activity).getImage(ImageSource.CAMERA){ bitmap -> 
            //do stuff
        }       
```

Or from device gallery

```kotlin
ImageProvider(activity).getImage(ImageSource.GALLERY){ bitmap -> 
            //do stuff
        }
            
```

That's all!

### Note

Camera permissions request is beyond the scope of this library, so make sure you have the appropiated permissions granted before calling any methods.

License
=======

    Copyright 2022 Álvaro Blanco Cabrero
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

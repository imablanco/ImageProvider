# ImageProvider Library

## Introduction

ImageProvider is an Android library that simplifies the process of capturing images from the camera or picking them from the gallery. It handles runtime permissions and provides a clean and easy-to-use API.

## Integration

To integrate ImageProvider into your Android project, follow these steps:

1.  **Add the JitPack repository to your root `build.gradle` file:**

    ```gradle
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```

2.  **Add the dependency to your app-level `build.gradle` file:**

    ```gradle
    dependencies {
        implementation 'com.github.user:ImageProvider:tag' // Replace user and tag with the actual values
    }
    ```

## Usage Examples

### Picking an image from the gallery

To pick an image from the gallery, initialize `ImageProvider` and call `getImage()` with `ImageSource.GALLERY`:

```kotlin
val imageProvider = ImageProvider(this) // 'this' refers to your Activity or Fragment

imageProvider.getImage(ImageSource.GALLERY) { bitmap ->
    // Handle the selected image (Bitmap)
    if (bitmap != null) {
        imageView.setImageBitmap(bitmap)
    } else {
        // Handle error or no image selected
    }
}
```

### Capturing an image from the camera

To capture an image using the camera, initialize `ImageProvider` and call `getImage()` with `ImageSource.CAMERA`:

```kotlin
val imageProvider = ImageProvider(this) // 'this' refers to your Activity or Fragment

imageProvider.getImage(ImageSource.CAMERA) { bitmap ->
    // Handle the captured image (Bitmap)
    if (bitmap != null) {
        imageView.setImageBitmap(bitmap)
    } else {
        // Handle error or no image captured
    }
}
```

**Note:** Ensure that your app has the necessary camera permissions (`android.permission.CAMERA`). ImageProvider helps with the image capture process but does not handle permission requests. You should request permissions before calling `getImage(ImageSource.CAMERA)`.

## Sample App

A sample app is available in the repository to demonstrate the library's functionality. You can find it in the `sample` directory.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue.

## License

This library is licensed under the MIT License. See the `LICENSE` file for details.

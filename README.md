# Dotted Progress Bar

## Introduction
Dotted progress bar. Use custom element or color to configure dots.

## Usage Instruction
Use color or element for inactiveDot and activeDot.
```xml
Create UI with Colour
<com.trncic.library.DottedProgressBar
    ohos:id="$+id:progress"
    ohos:width="match_parent"
    ohos:height="match_parent"
    ohos:padding="30vp"
    app:activeDot="$color:red"
    app:dotSize="29vp"
    app:inactiveDot="$color:black"
    app:jumpingSpeed="670"
    app:spacing="15vp" />

Create UI with Media-Image
<com.trncic.library.DottedProgressBar
    ohos:id="$+id:progress"
    ohos:width="match_parent"
    ohos:height="match_parent"
    ohos:padding="30vp"
    app:activeDot="$media:active_dot"
    app:dotSize="29vp"
    app:inactiveDot="$media:inactive_dot"
    app:jumpingSpeed="670"
    app:spacing="15vp" />
```
```java
DottedProgressBar progressBar = (DottedProgressBar) findComponentById(ResourceTable.Id_progress);
progressBar.startProgress();
progressBar.stopProgress();
```

## Installation instruction
### Method 1:
Generate the .har package through the library and add the .har package to the libs folder.
1. Add the .har package to the lib folder.
2. Add the following code to the gradle of the entry:
implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])

### Method 2:

```gradle
dependencies {
    implementation project(path: ':library')
}
```

### Method 3:
For using dotted-progress-bar from a remote repository in separate application, add the below dependency in entry/build.gradle file.

Modify entry build.gradle as below :
```gradle
dependencies {
       implementation 'io.openharmony.tpc.thirdlib:dotted-progress-bar:1.0.0'
}
```

License
-------

    Copyright 2015 Igor Trncic

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
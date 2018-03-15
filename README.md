# Hampang Adapter
Remove boilerplate for simple adapter inside recycler view<br />
Perfect match with using MVVM and databinding
<br />
<br />
Initialize adapter

```groovy
TAdapter tad = new TAdapter
                    (R.layout.fragment_hotlist_item, HotListViewModel.class);
```

<br />
Initialize it like usual

```groovy
recyclerView.setAdapter(tad);
```


<br />
You can also add filter rule

```groovy
tad.addFilterRule(new YourFilter(tad.getNotify()));
```

Download
--------

```groovy
buildscript {
  repositories {
    jcenter()

    maven { url "https://jitpack.io" }
   }
}
```

```groovy
dependencies {
  compile 'com.github.nirwannursabda:hampang-adapter:0.1.0'
}
```

License
-------

    Copyright 2018 Tech Atmosphere

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

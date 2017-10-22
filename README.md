# sbt-reactive-app

This project implements an SBT plugin that uses [SBT Native Packager](https://github.com/sbt/sbt-native-packager) and
reflection to build Docker images. These images contain metadata that is used with a CLI tool to create resources
for Kubernetes and potentially other target platforms.

### Development

##### Publish Locally

`sbt "^ publishLocal"`

### License

Copyright (C) 2017 Lightbend Inc. (https://www.lightbend.com).

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
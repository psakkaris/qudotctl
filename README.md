# qudotctl

The qudotcl project is a unified, containerized development runtime for classical simulation of quantum circuits
using the qudot suite of tools. These tools include:

1. The qudotc compiler that can compile qudot instruction language to qudot bytecode
2. The qudotvm high performance classical simulator for classical circuits that runs qudot bytecode
3. A containerized environment so everything "just works" locally
4. The qiskit-qudot connector so we can develop circuits in the higher level qiskit frameork

## Downloading and Installation

We can pull the container from: [INSERT CONTAINER URL WHEN PUBLISHED]
Note that the container image is large ~2GB. This is the tradeoff so we can package all required
runtimes, such as the intel-mkl runtime, in our image to eliminate any need for local setup.
After the image is downloaded in the container system of your choice (we use podman) then create an alias:
```bash
$ alias qudotctl="podman run -i --rm psakkaris/qudotctl"
$ qudotctl --help
```

## Developing and Running from Source

You can run the application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
You can specify subcommands via arguments as below:

```shell script
./mvnw compile quarkus:dev -Dquarkus.args='run bytecode'
```

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `qudotctl-1.0.0.0-SNAPSHOT.jar` file in the `target/` directory.
Be aware that it’s not an _über-jar_.

The application is now runnable using `java -jar target/qudotctl-1.0.0.0-SNAPSHOT.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

#### Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/qudotctl-1.0.0-SNAPSHOT-runner`

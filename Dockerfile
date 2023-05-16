FROM openjdk:11-jdk-slim

WORKDIR /src
COPY . /src

RUN apt-get update
RUN apt-get install -y dos2unix
RUN dos2unix gradlew

RUN bash gradlew shadowJar

WORKDIR /run
RUN cp /src/build/libs/*.jar /run/kalyan_server.jar

EXPOSE 8080

CMD java -jar /run/kalyan_server.jar

  #1. `FROM openjdk:11` - устанавливает OpenJDK для создания нового образа.
  #2. `WORKDIR /src` - устанавливает рабочую директорию для последующих команд.
  #3. `COPY . /src` - копирует содержимое текущей директории внутрь Docker-образа в директорию `/src`.
  #4. `RUN apt-get update` - обновляет список пакетов внутри Docker-образа.
  #5. `RUN apt-get install -y dos2unix` - устанавливает утилиту `dos2unix` для конвертации текстовых файлов из формата DOS/Windows в формат Unix.
  #6. `RUN dos2unix gradlew` - конвертирует файл `gradlew` в формат Unix.
  #7. `RUN bash gradlew shadowJar` - выполняет сборку проекта с помощью Gradle и создает JAR-файл.
  #8. `WORKDIR /run` - устанавливает директорию `/run` в качестве рабочей директории.
  #9. `RUN cp /src/build/libs/*.jar /run/server.jar` - копирует собранный JAR-файл внутрь Docker-образа и переименовывает его в `kalyan_server.jar`.
  #10. `EXPOSE 8080` - указывает на необходимость открыть порт 8080 в Docker-контейнере.
  #11. `CMD java -jar /run/server.jar` - задает команду, которая будет выполнена при запуске Docker-контейнера. В данном случае, запускается Java-приложение, указанное в файле `server.jar`.

  #1. docker build -t kalyan_server .
  # - `docker build` - команда для создания Docker-образа
  # - `-t kalyan_server` - флаг, который задает имя и тег для создаваемого образа (в данном случае имя образа - `kalyan_server`)
  # - `.` - точка означает текущую директорию, в которой находится Dockerfile, который будет использоваться для создания образа
  # Таким образом, команда создает Docker-образ с именем `ktor-docker` на основе Dockerfile, находящегося в текущей директории.

  #2. docker run --name kalyan_server -p 8080:8080 kalyan_server_image
  # - `docker run` - команда для запуска Docker-контейнера.
  # - `--name kalyan_server` - задает имя Docker-контейнера, который будет запущен. В данном случае, имя контейнера - `kalyan_server`.
  # - `-p 8080:8080` - маппит порт 8080 из Docker-контейнера на порт 8080 на хост-системе. То есть, приложение, запущенное внутри контейнера, будет доступно по адресу http://localhost:8080 на хост-системе.
  # - `kalyan_server_image` - имя Docker-образа, на основе которого будет создан и запущен контейнер.
  # Таким образом, команда запускает Docker-контейнер на основе образа `kalyan_server_image`, маппит порт 8080 из контейнера на порт 8080 на хост-системе и задает имя контейнера `kalyan_server`.

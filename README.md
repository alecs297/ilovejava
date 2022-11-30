# ILoveJava - A new revolutionary forum for Java lovers

## Project structure

- src/main
  - java/dev/palmes/ilovejava
    - controller : Controller class
    - models :  Data model descriptions
  - ressources : config files and other utils
  - webapp
    - static : static assets
    - WEB-INF
      - views : Data views


## MySQL default configuration

| Option         | value           |
|----------------|-----------------|
| Database name  | `ilovejava`     |
| MySQL User     | `ilovejava`     |
| MySQL Password | `Ilovejava123!` |

You can change the mysql connection details in `src/main/java/resources/application.properties`

## Tailwind styles build

`npm` is required for building the css files.

to generate the `style.css` file, install dependencies with
```shell
npm i
```

then launch the css building script

```shell
npm run build:postcss
```
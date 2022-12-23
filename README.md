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

## Demo setup

Default password for demo users is `Secure@password1234`

Some users are already created in the database, you can use them to login.

```
pierre.dupont@gmail.com
marie.martin@gmail.com
jacques.leblanc@gmail.com
chloe.durand@gmail.com
antoine.moreau@gmail.com
sophie.roy@gmail.com
etienne.duval@gmail.com
eva.lefevre@gmail.com
nathan.dubois@gmail.com

mod@palmes.dev (moderator)
```
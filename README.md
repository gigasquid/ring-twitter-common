
# ring-twitter-common

## [http://ring-twitter-common.herokuapp.com/](http://ring-twitter-common.herokuapp.com/)


This is a clojure web application using compojure that lets you find the twitter followers in common between two users.  
It is also deployed on Heroku Cedar (which is sooo cool).

## Usage

To use, you need to set up a Twitter Application http://twitter.com/apps/new.
Once you have your oauth-access-token and your
oauth-access-token-secret, you need to set them up in the config.clj file


## Installation
- Install Leiningen https://github.com/technomancy/leiningen
- lein deps
- lein ring server

## Magic to Deploy to Heroku
The magic is in the Procfile to kick off the app
* heroku create --stack cedar
* git push heroku master


## License

Copyright (C) 2011 Carin Meier

Distributed under the Eclipse Public License, the same as Clojure.


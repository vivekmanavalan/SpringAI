Steps to Run:
* Run the docker file using the command docker compose up -d
* To check the keys that are cached run -> redis-cli and then KEYS *

How this works:
* Spring AI lets redis cache the questions and answers semantically.
* When a semantically similar question with a threshold of 0.9 (configurable) is asked again then it replies from the cache
* SemanticCacheAdvisor and MessageChatMemory adivsors help to cache these messages. 

Reference: https://thetalkingapp.medium.com/spring-ai-recipe-semantic-caching-548274a1733a
Check BeanOutputConverter.class for more information about the structured output

We can also add the default log advisor to know what's happening under the hood
to know how the context and our json is passed as a schema to get the response in that format
{
"blogs": [
{
"title": "Spring AI with Model Context Protocol (MCP)",
"author": "Christian Tzolov",
"link": "https://spring.io/blog/2024/11/25/spring-ai-with-model-context-protocol-mcp"
},
{
"title": "Getting Started with Spring AI and Model Context Protocol (MCP)",
"author": "Dan Vega",
"link": "https://www.danvega.dev/blog/spring-ai-mcp"
},
{
"title": "Spring AI and the Model Context Protocol (MCP)",
"author": "Thomas Vitale",
"link": "https://www.thomasvitale.com/spring-ai-model-context-protocol/"
}
]
}


Persisting Memory:
In this project we've persisted the chats into Postgres DB so that the LLM remembers the context.
Number of messages that will be persisted is also configurable.

For more Info: https://www.linkedin.com/pulse/spring-ai-recipe-summarizing-chatmemory-craig-walls-rdahc/
Sample Curl: 

curl --location 'http://localhost:8080/ask' \
--header 'Cookie: X-CONV-ID=conv123' \
--header 'Content-Type: application/json' \
--data '{
"question": "Who won the Premier League football?"
}'
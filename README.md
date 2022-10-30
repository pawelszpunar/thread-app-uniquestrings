# Thread App
API endpoints info

## Start new thread example
```
POST http://localhost:8080/tasks
```
Json example:
```
{
	"min": 1,
	"max": 3,
	"howMuchStrings": 10,
	"characters": "abcd"
}
```
## Get threads summary with id and status
```
GET http://localhost:8080/tasks/summary
```
Response example
```
{
	"threads running": 0,
	"threads ended": 2
}
```
## Get all threads with id and status
```
GET http://localhost:8080/tasks/
```
Response example:
```
[
	{
		"id": 1,
		"status": "ended"
	},
	{
		"id": 2,
		"status": "ended"
	}
]
```
## Get thread info by id
```
GET http://localhost:8080/task/{id}
```
Json example
```
GET http://localhost:8080/task/1
```
Response example
```
{
	"id": 1,
	"status": "ended"
}
```
## Get thread result as txt file
```
GET http://localhost:8080/download/{id}
```
Json example
```
GET http://localhost:8080/download/1
```

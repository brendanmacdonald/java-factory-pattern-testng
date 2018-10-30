Factory Pattern used for driver creation during UI automation tests.

Executed with:
```
mvn clean test
```

Generate the allure report to a folder:
```
 mvn allure:report
```

Generate the allure report and have it auto-display in a browser:
```
 mvn allure:serve
```


To add Categories to the allure report, create a file called ```categories.json``` inside ```target\allure-results``` (prior to report generation) that contains the following:
```
[
  {
    "name": "Ignored tests",
    "matchedStatuses": ["ignored"]
  },
  {
    "name": "Failed tests",
    "matchedStatuses": ["failed"]
  },
  {
    "name": "Test defects",
    "matchedStatuses": ["broken"]
  }
]
```
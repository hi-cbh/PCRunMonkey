@ECHO OFF

ECHO.[Program is starting , please wait........]

java -jar ./run.jar>"%date:~0,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%.log"

ECHO.[.....End........]

pause
@ECHO NO
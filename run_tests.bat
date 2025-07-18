@echo off
echo Starting tests in Chrome browser...
call mvn clean test
echo.
echo Chrome tests completed. Starting Edge tests...
echo.
call mvn clean test -P edge

echo.
echo All tests completed.
echo Check the target/surefire-reports folder for results.
exit
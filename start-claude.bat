REM Get first line of lms status output
  @echo off
  setlocal enabledelayedexpansion
  :start

REM Check llm studio server status
  echo Check llm server status:
  set "output_cnt=0"
  for /F "delims=" %%f in ('lms status') do (
    set /a output_cnt+=1
    set "output_!output_cnt!=%%f"
  )

REM If server is offline startit and check status again.
  echo "%output_1%" | findstr /i "off" > nul
  if %errorlevel% equ 0 (
    echo ^>^> LLM Studio is OFFLINE, Starting server...
    lms server start
    goto :start
  )

REM Server is started
  echo "%output_1%" | findstr /i "on" > nul
  if %errorlevel% equ 0 (
    echo ^>^> LLM Studio is ONLINE.
  )

REM Server is started
 echo Check loaded model:
 echo "%output_2%" | findstr /i "No"> nul
  if %errorlevel% equ 0 (
    echo *** START LM Studio and load model, then try again ***
    pause
    endlocal
  ) else (
    REM Extract model name running
    for /f %%a in ("!output_3:~5!") do set "MODEL_NAME=%%a"
  )

echo Running model: "%MODEL_NAME%"
echo Start Claude Code
start "" claude --model %MODEL_NAME%
timeout /t 10
endlocal

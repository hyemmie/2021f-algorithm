#!/usr/bin bash
echo "-compile start-"

# Compile
javac Solution3.java -encoding UTF8

start=$SECONDS

echo "-execute your program-"

# 시간제한 1초
timeout 1 java Solution3

# testset의 실행에 소요된 시간
echo "Execution time : $((SECONDS-start)) seconds"

echo "-print wrong answers-"


#결과가 정답과 다를 경우 그 위치를 출력
cmp output.txt answer.txt

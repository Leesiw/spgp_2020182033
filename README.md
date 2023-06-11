# spgp_2020182033
2023-1학기 스마트폰 게임 프로그래밍
2020182033 이시우

## **리듬히어로 모작**

![1975691_1](https://user-images.githubusercontent.com/84753357/229353407-0cff67f6-66df-4116-a780-f95bab9c049d.png)

장르 : 리듬 액션 게임

## 게임 컨셉

#### 신나는 음악에 맞춰 화면을 터치/슬라이드하자!
음악에 맞춰 나타나는 마크를 정확한 박자에 터치/슬라이드 해 엘리트 게이지를 높게 유지시키자

엘리트 게이지가 0에 도달할 시 게임 오버!


![엘리트_게이지](https://user-images.githubusercontent.com/84753357/229353783-f21cf047-e8f8-4ac8-bd21-34c64a3e91ad.png)

## 현재까지의 진행 상황
![진해상황](https://github.com/Leesiw/spgp_2020182033/assets/84753357/2f8bf521-46b0-476c-bc29-b9bc4e0b8bb8)


## Git Commit
#### github insights commit
![commitinsights](https://github.com/Leesiw/spgp_2020182033/assets/84753357/b1b677de-cf30-4c43-af9c-cc74ad249231)



#### 주차별 커밋 횟수

1주차 : 1

2주차 : 20

3주차 : 0

4주차 : 16

5주차 : 29

6주차 : 3

7주차 : 26

8주차 : 0

9주차 : 16

## 사용된 기술
- json을 이용한 데이터 저장과 json 파싱 기능


## 참고한 것들
- 수업 내용 

- 구글 검색을 통해 찾을 수 있는 자바 관련 코드들


## 수업 내용에서 차용한 것
- 프레임워크

- Paused Scene

- Button

- Score

- Sound

- Sprite, AnimSprite 등


## 직접 개발한 것

#### 히트 마크
-시간에 따라  원이 줄어들고 터치한 타이밍에 따라 점수마크 (별모양 - 300/100/50, x)를 생성하며 소멸한다

-아예 터치 안한다면 일정 시간 후 x 점수 마크 생성 후 소멸

![hitmark](https://user-images.githubusercontent.com/84753357/236692741-9d596c5d-d49e-4699-bde8-7f41d1704bde.gif)


#### 슬라이드 마크
-시작 지점은 히트마크와 비슷하게 동작(시간에 따라 원이 줄어든다)

-원이 다 줄어들면 공이 반대편으로 굴러간다

-해당 공을 터치하고 있는지를 10번에 나눠서 체크. 터치하고 있다면 30점 씩 얻는다

![slidemark](https://user-images.githubusercontent.com/84753357/236692786-10612104-8f84-47b7-9f43-dd4d8d0dcf39.gif)


#### 스핀 마크
-스핀마크 중앙점 기준 좌표계에서 터치 지점까지의 각을 계산

-이전 각과 비교해 스핀 마크를 회전 시키고 게이지를 상승 시킨다

-끝나는  시점의 게이지 점수에 따라 300/100/50/x 중 하나의 점수를 부여한다

![spinmark](https://user-images.githubusercontent.com/84753357/236692840-54634fd3-a6e9-42a9-a2ca-e2eb76829ca3.gif)


#### 게이지

-시간에 따라 일정하게 줄어든다

-점수를 획득할 때마다 게이지가 상승

-게이지가 0이 되면 게임 오버씬으로 넘어간다

![gauge](https://user-images.githubusercontent.com/84753357/236692975-51789928-8441-464b-b85b-9da4a2cc6093.gif)

#### score 마크

- 생성 시 부터 투명도가 점점 높아져 완전히 투명해지면 제거된다


## 아쉬운 것
- 채보 박자가 정확히 맞지 않는 부분들이 많았던 것

- 시간이 부족해 곡을 약 2분 분량으로 자른 것

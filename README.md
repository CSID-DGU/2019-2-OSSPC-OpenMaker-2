<p>
    <img src="https://img.shields.io/badge/version-1.0.0-rgb(26, 188, 156).svg" />
    <img alt="IDE" src="https://img.shields.io/badge/IDE-VSCode/Eclipse-rgb(26, 188, 156).svg" />
    <img src="https://img.shields.io/badge/Ubuntu-16.04-rgb(26, 188, 156).svg" />
    <img src="https://img.shields.io/badge/java-1.8-blue.svg" />   
    <img alt="License" src="https://img.shields.io/badge/License-General Public License_2.0-yellow.svg"/>
</p>


<p>

# 기존 게임 Github 



<https://github.com/ProgrammingHeroes/SuperMarioJava





## 기존 게임 Screenshots





![](https://images-blogger-opensocial.googleusercontent.com/gadgets/proxy?url=http://1.bp.blogspot.com/-khsDa-q-Dd8/U5pM21ZJpdI/AAAAAAAAK0A/2LkrXeCV8f8/s1600/Sin%2Bt%25C3%25ADtulo.png&container=blogger&gadget=a&rewriteMime=image/*)

![](https://images-blogger-opensocial.googleusercontent.com/gadgets/proxy?url=http://3.bp.blogspot.com/-iQHGVdGC98I/U5pM22u2jkI/AAAAAAAAKz0/OWCP6X6uVJ0/s1600/Sin%2Bt%25C3%25ADtulo1.png&container=blogger&gadget=a&rewriteMime=image/*)

![](https://images-blogger-opensocial.googleusercontent.com/gadgets/proxy?url=http://3.bp.blogspot.com/-cLKQMJDKJIs/U5pM2xMnu_I/AAAAAAAAKzw/O3oger-2b3g/s1600/Sin%2Bt%25C3%25ADtulo2.png&container=blogger&gadget=a&rewriteMime=image/*)



</p>

------






# 다운 및 실행방법





```bash
git clone https://github.com/CSID-DGU/2019-2-OSSPC-OpenMaker-2.git
```



- workspace로 clone 받은 상위 폴더를 지정해주고  다음에 뜨는 창에서 import project 클릭

  ![1572257354509](assets/1572257354509.png)



- 다음 창에서 General - Existing Projects into Workspace 클릭

![1572257481719](assets/1572257481719.png)



- clone한 폴더를 선택하고  3번 칸에 해당하는 부분이 나왔는지 꼭!!! 확인하셔야 됩니다.
  저는 지금 이미 프로젝트를 import한 상태여서  Finish 버튼이 비활성화되어 있는데요,
  처음에 하시면 활성화되어있는 Finish버튼 누르시면 끝입니다.
  
    ![1572364685962](assets/1572364685962.png)





# 사용설명서(기존 게임과 비교)

 

- 캐릭터는 좌우로 움직일 수 있고 space 키로 점프할 수 있다.

- Shift키를 누르고 움직이거나 점프하면 이동속도 및 점프력이 증가한다.

- 캐릭터와 brick, coin, stage 등 오브젝트들의 상호작용을 통한 게임플레이 제공

- 모든 coin을 먹고 다음 포탈을 통해 다음 Stage로 이동하면 하트 +1.(기존 게임은 coin을 다먹으면 자동으로 Stage 이동)

  

  - **기존 게임**
    ![원본_코인다먹 stage이동](https://user-images.githubusercontent.com/41337277/70543007-1d1db600-1bad-11ea-9ffe-acea2dd58be4.gif)

    

  - **현재 게임**
    ![하트+!](https://user-images.githubusercontent.com/41337277/70543269-7a196c00-1bad-11ea-97f0-e88aa8582f15.gif)

  

- Stage 이동은 포탈에 마리오가 닿으면 이동한다.(코인 남기고 이동 시 하트 보너스 없음)

- 코인은 캐릭터에 닿으면 자동으로 획득할 수 있다. 

- 낭떠러지(불구덩이)로 떨어지면 해당 스테이지로 초기화 되며 목숨(하트)가 하나 줄어든다.

- 하트가 0이 되면 게임은 종료된다.
  
  

  - **하트기능 및 낭떠러지 기능**

    ![플젝_하트기능, 낭떠러지](https://user-images.githubusercontent.com/41337277/70543415-b3ea7280-1bad-11ea-9a7c-6a0a6383cd41.gif)






     ![AC_[20191119-142409]](assets/AC_%5B20191119-142409%5D.gif)







------

# 기능 개발 상세



## 낭떠러지 기능



- 맨 밑 바닥에 불 사진 추가 [/res/img/loader 에서 이미지경로 세팅]

- 불에 닿았을 때[Fire.java, collision()] [Map.java makeThisMap()] 현재 맵 다시 생성 및 마리오 처음 위치로 초기화 
  
  

  <img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/d4a73ec1921de1369fdba38925612808/image.png" />

  



## Next Stage 기능 



1. portal 객체에 collision기능이 있으므로 포탈에 닿았을 때 체크하는 static boolean checkTouchPortal값 만듦

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/1bb969abfec2e8e1991c10c29e77585a/image.png" />

2. 포탈에 닿을 시 checkTouchPortal 값 true 변경

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/54f5be8af55827cf861635677a156032/image.png" />

3. Main에 updateStage()메서드는 다음판으로 가는 메서드인데 코드내용 변경
   --> 조건을 Portal.checkTouchPortal이 true면 다음스테이지로 변경
   
   

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/9784e1e7df106b29457e175a6716f3dc/image.png" />



--------------



## 목숨 기능



1. 화면의 고정된 하트 개수를 표시(Graphics2D 사용)
   
   

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/6eabbe529e3de94297bc5d8643858644/image.png" />



2. Player(마리오) 클래스에 하트 필드추가. 필드가 static으로 설정한 이유는 죽었을 때 새로운 맵을 생성하게 되거나 다음판으로 갔을 때 새로운 마리오 인스턴스를 추가하게 되는데 이러한 경우에도 하트 개수를 유지할 수 있다.
   
   

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/0ff112436a49492ae0416fe38aecf661/image.png" />



3. 맵에 있는 코인 다 먹고 다음 판으로 이동시 하트 1개 증가 (하트 다 먹었을 때 한 개 증가 하게 하려고 했지만 heartUp()메서드를 어디서 계속 돌려야할지 모르겠어서 이렇게 구성함)
   
   

<img src="https://trello-attachments.s3.amazonaws.com/5ce50ae5d517196a23ddc8f6/5defb678dc675d551ec8fc73/44161dc1e417824736a15eeb32ad1ef4/image.png" />

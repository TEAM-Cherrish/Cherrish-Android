# <img width="200" height="100" alt="logo" src="https://github.com/user-attachments/assets/7cde32c0-f76c-4cf0-b3bf-260befb360a1" />
  
<p align="center">  
  <img width="800" height="400" alt="image" src="https://github.com/user-attachments/assets/7f0e0645-fdf2-43a7-ab38-86f08377e466" />  
</p>  

# <img src="https://github.com/user-attachments/assets/b7c69bb0-3579-4074-8eca-e7966128fffb" height="50"/> Cherrish 서비스 소개
> 미용 의료부터 관리 루틴까지, 개인의 추구미에 맞는 관리의 방향을 정리하고, 다운타임&일정 중복을 방지하는 뷰티 캘린더


<br/>

## <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="40"/>Cherrish 주요 기능
- 피부 고민 키워드 기반 시술 리스트
- 시술 다운타임 설정 및 디데이 여유기간 시각화
- AI가 짜주는 챌린지 루틴 추천
- 챌린지 기반 체리 게이미피케이션


<br/>

## <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="40"/>Tech Stack  
  
| Category | Stack |  
| --- | --- |  
| **Architecture** | Google Recommended App Architecture |  
| **UI** | Jetpack Compose |  
| **DI** | Dagger-Hilt |  
| **Asynchronous** | Kotlin Coroutine, Flow |  
| **Modularization** | Single Modularization |  
| **Build Configuration** | Gradle Version Catalog, Custom Convention Plugins |  

<br/>

### <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="30"/> Tech Stack 소개 ###

**1️⃣ Architecture: Google Recommended App Architecture** <br/>
구글 권장 아키텍처는 UI–상태–비즈니스 로직–데이터를 명확히 분리하여 코드의 책임을 분명하게 만들기 위해 사용했다. 
이 구조는 테스트와 유지보수를 쉽게 하고 기능이 커져도 안정적으로 확장할 수 있다고 판단했다. 
또한 Android Jetpack(ViewModel, Flow, Compose 등)과 자연스럽게 연동되어 공식 가이드와의 일관성을 유지할 수 있어 선택하였다.

**2️⃣ Pattern: MVVM** <br/>
러닝커브가 조금 높은 MVI를 적용하기보다 MVVM을 조금 더 체계적으로 사용해보고자 도입했습니다.

**3️⃣ Dependency Injection: Hilt** <br/>
의존성 주입으로 뷰모델 관리를 더욱 편하게 하고자 도입했습니다.
구글이 공식 지원하는 DI 라이브러리로 보일러플레이트 코드를 최소화할 수 있습니다.

**4️⃣ Navigation: Type-Safety Navigation**<br/>
기존 문자열 기반 네비게이션은 런타임 오류를 유발할 수 있기 때문에 타입 안정성을 지원하는 Type-Safety Navigation을 도입했습니다.

<br/>

## <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="40"/>**Convention**

💫 [Git & Branch Convention](https://www.notion.so/2d5fe06db52581bca022f39c34cdb3f5)<br/>
✍️ [Code Convention](https://www.notion.so/2d5fe06db52581d099b6cb0fa89a4c62)<br/>
📂 [Packaging Convention](https://alpine-marten-2ce.notion.site/2d5fe06db525810f8a53ccbb507fbd0b?pvs=74)<br/>

<br/>

## <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="40"/>Contributors  
  
| 🤴김나현<br/>[@nhyeonii](https://github.com/nhyeonii) | 🍒정소희<br/>[@sohee6989](https://github.com/sohee6989) | 🍒남궁혜민<br/>[@hyeminililo](https://github.com/hyeminililo) | 🍒유수현<br/>[@usuuhyn](https://github.com/usuuhyn) |
| --- | --- | --- | --- |
| <img src="https://github.com/user-attachments/assets/5282584d-a280-499c-8140-ea7cde29edfb" height="280" /> | <img src="https://github.com/user-attachments/assets/a215c04e-4f18-4a47-8f3e-a3520eeb6740" height="280" /> | <img src="https://github.com/user-attachments/assets/11751437-59c7-403e-87de-5c3b94394ace" height="280" /> | <img src="https://github.com/user-attachments/assets/bc723e84-4279-449f-af4c-d7b6d2372457" height="280" /> | <img src="https://github.com/user-attachments/assets/6bedede6-fd60-4514-90ad-7c596ce41fbb" height="280" /> |  
| `캘린더` | `홈` `온보딩` | `챌린지` `마이` | `시술` |

<br/>

## <img src="https://github.com/user-attachments/assets/ac0bd614-bf51-4fde-9727-f6f3d70dafa2" height="40"/>**Foldering**
```
📂 cherrish
┣ 📂 core
┃ ┣ 📂 common
┃ ┣ 📂 designsystem
┃ ┣ 📂 local
┃ ┣ 📂 network
┃ ┣ 📂 util
┣ 📂 data
┃ ┣ 📂 di
┃ ┣ 📂 local
┃ ┣ 📂 model
┃ ┣ 📂 remote
┃ ┣ 📂 repository
┃ ┣ 📂 repositoryimpl  
┣ 📂 presentation
┃ ┣ 📂 calendar
┃ ┣ 📂 challenge
┃ ┣ 📂 home
┃ ┣ 📂 main
┃ ┣ 📂 mypage
┃ ┣ 📂 onboarding
┃ ┣ 📂 splash

```
  
---  

<p align="center">  
  Made with by Cherrish
</p>

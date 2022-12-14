# FP-OOP-Snake-Game
## Final Project for OOP

Name: Riski Ilyas<br>
NRP : 5025211189

Reference: https://www.youtube.com/watch?v=bI6e6qjJ8JQ&t=1532s

## Description
This project is a simple GUI game called Snake Game. This game has 10 levels that needs to be completed and each level has different diffculties and obstacles. There is also Casual Mode to play the game with no obstacles. There is also a Highscore that records your top score in each Casual Game. Besides the gameplay, it also has Settings with multiple options such as Changing Player Name, Turn On/Off Music, Turn On/Off SFX, & Reset Game. All the settings data will be stored in an external file so that the game still remember the data even when the Game has Closed.

## Screenshots
<p>
<img src="https://user-images.githubusercontent.com/71499142/206075208-4c025cf3-e955-4c93-b3af-7c46df12f1f6.png" width="400"/>
<img src="https://user-images.githubusercontent.com/71499142/206075309-67e9861e-db01-4bde-8ca0-ad7a6eb7f8ee.png" width="400"/>
<img src="https://user-images.githubusercontent.com/71499142/206075355-3fdce1e9-1d09-44fd-9182-9a677de43904.png" width="400"/>
<img src="https://user-images.githubusercontent.com/71499142/206075433-1d9d0887-5d86-4a13-83e0-9a663101f8ac.png" width="400"/>
<img src="https://user-images.githubusercontent.com/71499142/206075504-f00c2986-d5f6-4cb1-9cc1-08682db4db0b.png" width="400"/>
<img src="https://user-images.githubusercontent.com/71499142/206075563-7fdcc839-b4fe-4ea9-8928-d49e2508cba3.png" width="400"/>
</p>

## To-do

- [x] Casting/Conversion (Casting GameMusic)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/sound/SoundManager.java#L33
- [x] Constructor (Constructors for each screen)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/GameScreen.java#L42
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/LevelScreen.java#L27
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/MenuScreen.java#L28
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/SettingsScreen.java#L26
- [x] Overloading (Overloading at screenRouter to route to gamescreen)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/ScreenRouter.java#L35-L42
- [x] Encapsulation (Observable encapsulation on DataStore)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L18-L22
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L64-L66
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L73-L75
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L82-L84
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L91-L93
- [x] Inheritance (Inheritance on Buttons)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/components/Button.java#L5
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/components/BackButton.java#L3
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/components/MenuButton.java#L5
- [x] Polymorphism (Level polymorphism on GameScreen)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/GameScreen.java#L38
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/GameScreen.java#L226-L240
- [x] ArrayList (Arraylist for Linsteners on Observable)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/Observable.java#L8
- [x] Exception Handling (Exception Handling on DataStore)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L29-L44
- [x] GUI (Java Swing)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/GameFrame.java#L6-L22
- [x] Interface (Interface for callback on Observable)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/Observable.java#L30-L32
- [x] Abstract Class (Abstract class for Screen and Level)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/screens/Screen.java#L7-L19
- [x] Generics (Pair Class)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/Pair.java#L3-L20
- [x] Collection (Hashmap on SounManager)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/sound/SoundManager.java#L11
- [x] Input Output (I/O for the dataStore)
  https://github.com/riskiilyas/FP-OOP-Snake-Game/blob/df0c463ebc5cadc9095b4506e3c44c1aa49527d0/src/main/java/utils/DataStore.java#L29-L62

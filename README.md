<h1>파일 업로드/저장 예제</h1>

<h2>설명</h2>
<pre>
- 파일의 아이디는 UUID로 설정했다. 해당 설명은 File.java에 있다.
  일반적인 uuid는 db에 저장시 오류가 나서 바이너리 형식으로 저장함
- Jpa Repository 또한 pk를 UUID로 주었다.
- 다중 파일 업로드 & 다운로드가 가능하게 만들었다.
  db 저장과 별개로 dto객체를 다시 한번 선언해서 리스트에 넣어서 화면에 뿌려주는 방식을 사용했다.
</pre>

<h2>사진 설명</h2>
홈화면
<img src="https://user-images.githubusercontent.com/88976237/178189175-aa3ee1fa-a38a-409e-90a6-7d6a70c90c59.png">
<hr>

다중파일 선택
<img src="https://user-images.githubusercontent.com/88976237/178189206-c5da0176-9123-40d6-b7e0-a2bc6062c63d.png">
<hr>

다중파일 업로드
<img src="https://user-images.githubusercontent.com/88976237/178189215-dec4af1d-ab4a-4679-bda0-631db5070e6f.png">
<hr>

다중파일 다운로드(서로 다른 파일들임)
<img src="https://user-images.githubusercontent.com/88976237/178189221-e3faa96d-47ca-472d-9771-b2491358205f.png">

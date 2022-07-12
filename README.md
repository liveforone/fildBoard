<h1>파일 업로드/저장 예제</h1>

<h2>설명</h2>
<pre>
- 파일의 아이디는 UUID로 설정했다. 해당 설명은 File.java에 있다.
  일반적인 uuid는 db에 저장시 오류가 나서 바이너리 형식으로 저장함
- Jpa Repository 또한 pk를 UUID로 주었다.
- 다중 파일 업로드 & 다운로드가 가능하게 만들었다.
  db 저장과 별개로 dto객체를 다시 한번 선언해서 리스트에 넣어서 화면에 뿌려주는 방식을 사용했다.
- 파일을 불러올때는 uuid + _ + fileName으로 가져와야한다. 왜냐하면 transferTo()로 저장할떄
  uuid + _ + fileName 으로 저장하기 때문이다. 이를 꼭 주의하자. image.html은 딱히 필요없다.
- 후에 게시판에 이를 적용할때에는 업로드 후에 redirect:/board로 게시판으로 리다이렉트 해주고
  게시글을 띄어주는 detail에서 이미지보여주는 showImage에 repository를 통해 uuid와 fileName을
  가져오고 uuid + _ + fileName으로 불러와서 뷰를 띄어주기만 하면 된다. 이때 테스트 해봐야하는것은
  다중파일이기때문에 new로 dto를 생성해서 list에 넣어 보내줘야하는지 또는 그냥 dto를 list에 넣어
  보내줄 수 있는지 확인후 알맞는 방식으로 진행하자. db에는 uuid와 fileName이 모두 담겨있기에 걱정말자.
- 이미지를 올려서 그렇지 다른 파일도 가능하다.(audio, mp4) 기본적으로 html단만 변경되지
  컨트롤러단은 건드릴게 없다.
</pre>
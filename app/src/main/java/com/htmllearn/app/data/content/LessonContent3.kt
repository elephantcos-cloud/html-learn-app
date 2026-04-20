package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.*

val chapter3 = Chapter(
    id = "ch3", title = "Tables", description = "HTML দিয়ে সুন্দর table তৈরি",
    color = 0xFF4CAF50,
    lessons = listOf(
        Lesson(
            id = "ch3_l1", chapterId = "ch3", title = "Basic Table Structure", xp = 30, duration = "৮ মিনিট",
            explanation = """
📌 Table এর মূল tags:
<table> — table শুরু
<tr> — table row (সারি)
<th> — table header (শিরোনাম cell)
<td> — table data (সাধারণ cell)

📌 Table Sections:
<thead> — header অংশ
<tbody> — body/data অংশ
<tfoot> — footer অংশ

📌 caption:
Table এর শিরোনাম দেওয়া যায়।

📌 border attribute:
পুরানো পদ্ধতি। CSS ব্যবহার করা ভালো।
            """.trimIndent(),
            codeExample = """<table border="1" style="width:100%">
  <caption>ছাত্রদের তালিকা</caption>
  <thead>
    <tr>
      <th>নাম</th>
      <th>রোল</th>
      <th>নম্বর</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>রাহেলা</td>
      <td>01</td>
      <td>95</td>
    </tr>
    <tr>
      <td>করিম</td>
      <td>02</td>
      <td>88</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="2">গড় নম্বর</td>
      <td>91.5</td>
    </tr>
  </tfoot>
</table>""",
            outputHtml = """<table style="width:100%;border-collapse:collapse;background:#1e1e1e;border-radius:8px;overflow:hidden"><caption style="color:#6C63FF;font-weight:bold;padding:8px">ছাত্রদের তালিকা</caption><thead><tr style="background:#6C63FF"><th style="padding:10px;color:white">নাম</th><th style="padding:10px;color:white">রোল</th><th style="padding:10px;color:white">নম্বর</th></tr></thead><tbody><tr><td style="padding:10px;border-bottom:1px solid #333;color:white">রাহেলা</td><td style="padding:10px;border-bottom:1px solid #333;color:white">01</td><td style="padding:10px;border-bottom:1px solid #333;color:white">95</td></tr><tr><td style="padding:10px;border-bottom:1px solid #333;color:white">করিম</td><td style="padding:10px;border-bottom:1px solid #333;color:white">02</td><td style="padding:10px;border-bottom:1px solid #333;color:white">88</td></tr></tbody><tfoot><tr style="background:#333"><td colspan="2" style="padding:10px;color:#ccc">গড় নম্বর</td><td style="padding:10px;color:#4CAF50;font-weight:bold">91.5</td></tr></tfoot></table>""",
            quizzes = listOf(
                Quiz("<th> এবং <td> এর পার্থক্য?",listOf("কোনো পার্থক্য নেই","<th> header cell, <td> data cell","<td> header, <th> data","দুটো same"),1,"<th> header cell — bold হয়। <td> সাধারণ data cell।"),
                Quiz("colspan কী কাজ করে?",listOf("Row merge করে","Column merge করে","Table delete করে","Border যোগ করে"),1,"colspan দিয়ে একাধিক column একত্রিত করা যায়।")
            )
        ),
        Lesson(
            id = "ch3_l2", chapterId = "ch3", title = "Table Spanning", xp = 35, duration = "৯ মিনিট",
            explanation = """
📌 colspan:
একটি cell একাধিক column জুড়ে বিস্তার করে।
colspan="3" — তিনটি column একত্রে।

📌 rowspan:
একটি cell একাধিক row জুড়ে বিস্তার করে।
rowspan="2" — দুটি row একত্রে।

📌 ব্যবহার:
Report, Schedule, জটিল data দেখাতে।
Complex table বানাতে।

📌 CSS দিয়ে সুন্দর Table:
border-collapse: collapse — border গুলো একত্রিত করে।
nth-child দিয়ে stripe effect।
hover effect যোগ করা যায়।
            """.trimIndent(),
            codeExample = """<table style="border-collapse:collapse;width:100%">
  <tr>
    <th colspan="3" style="background:#6C63FF;
        color:white;padding:12px">
      সাপ্তাহিক রুটিন
    </th>
  </tr>
  <tr>
    <td rowspan="2" 
        style="background:#1a1a2e;
               color:white;padding:10px;
               text-align:center">
      সকাল
    </td>
    <td style="padding:10px">সোমবার: গণিত</td>
    <td style="padding:10px">মঙ্গলবার: বিজ্ঞান</td>
  </tr>
  <tr>
    <td style="padding:10px">বুধবার: বাংলা</td>
    <td style="padding:10px">বৃহস্পতি: ইংরেজি</td>
  </tr>
</table>""",
            outputHtml = """<table style="border-collapse:collapse;width:100%;color:white"><tr><th colspan="3" style="background:#6C63FF;padding:12px">সাপ্তাহিক রুটিন</th></tr><tr><td rowspan="2" style="background:#1a1a2e;padding:10px;text-align:center;border:1px solid #333">সকাল</td><td style="padding:10px;border:1px solid #333">সোমবার: গণিত</td><td style="padding:10px;border:1px solid #333">মঙ্গলবার: বিজ্ঞান</td></tr><tr><td style="padding:10px;border:1px solid #333">বুধবার: বাংলা</td><td style="padding:10px;border:1px solid #333">বৃহস্পতি: ইংরেজি</td></tr></table>""",
            quizzes = listOf(
                Quiz("colspan='4' মানে কী?",listOf("4টি row জুড়ে","4টি column জুড়ে","4 pixel border","4 unit padding"),1,"colspan='4' মানে এই cell 4টি column জুড়ে বিস্তৃত।"),
                Quiz("border-collapse:collapse কী করে?",listOf("Border মুছে দেয়","Double border এড়িয়ে একক border দেখায়","Table বড় করে","Color বদলায়"),1,"border-collapse:collapse দিলে cells এর border গুলো merge হয়।")
            )
        ),
        Lesson(
            id = "ch3_l3", chapterId = "ch3", title = "Forms — Input এর জগৎ", xp = 40, duration = "১২ মিনিট",
            explanation = """
Form দিয়ে user এর কাছ থেকে data নেওয়া হয়।

📌 <form> tag:
action — data কোথায় পাঠাবে
method — GET বা POST

📌 <input> types:
text — সাধারণ text
email — email format
password — লুকানো text
number — সংখ্যা
tel — phone number
url — website address
date — তারিখ
time — সময়
checkbox — multiple choice
radio — single choice
file — file upload
range — slider
color — color picker
submit — submit button
reset — reset button
hidden — লুকানো field

📌 <label>:
Input এর সাথে label যুক্ত করে।
for attribute দিয়ে input এর id match করতে হয়।

📌 <select> ও <option>:
Dropdown menu তৈরি করে।
            """.trimIndent(),
            codeExample = """<form action="/submit" method="POST">
  <!-- Text Input -->
  <label for="name">নাম:</label>
  <input type="text" 
         id="name" 
         name="name"
         placeholder="নাম লিখুন"
         required>

  <!-- Email -->
  <label for="email">ইমেইল:</label>
  <input type="email" id="email"
         placeholder="email@example.com">

  <!-- Password -->
  <label for="pass">পাসওয়ার্ড:</label>
  <input type="password" id="pass"
         minlength="8">

  <!-- Radio -->
  <p>লিঙ্গ:</p>
  <input type="radio" name="gender" 
         value="male" id="male">
  <label for="male">পুরুষ</label>
  <input type="radio" name="gender"
         value="female" id="female">
  <label for="female">মহিলা</label>

  <!-- Select -->
  <label for="class">শ্রেণি:</label>
  <select id="class" name="class">
    <option value="">বেছে নিন</option>
    <option value="9">নবম</option>
    <option value="10">দশম</option>
    <option value="11">একাদশ</option>
  </select>

  <!-- Submit -->
  <button type="submit">জমা দিন</button>
</form>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><label style="color:#00BCD4">নাম:</label><br><input type="text" placeholder="নাম লিখুন" style="width:100%;padding:8px;margin:4px 0 12px;background:#2a2a2a;border:1px solid #555;border-radius:6px;color:white;box-sizing:border-box"><br><label style="color:#00BCD4">ইমেইল:</label><br><input type="email" placeholder="email@example.com" style="width:100%;padding:8px;margin:4px 0 12px;background:#2a2a2a;border:1px solid #555;border-radius:6px;color:white;box-sizing:border-box"><br><button style="background:#6C63FF;color:white;padding:10px 24px;border:none;border-radius:8px;cursor:pointer">জমা দিন</button></div>""",
            quizzes = listOf(
                Quiz("Form এর method='POST' কী করে?",listOf("Data URL এ দেখায়","Data লুকিয়ে পাঠায়","Data delete করে","Form reset করে"),1,"POST method data body তে পাঠায় — URL এ দেখা যায় না।"),
                Quiz("required attribute কী করে?",listOf("Field optional করে","Field পূরণ না করলে submit হয় না","Field disable করে","Field hide করে"),1,"required attribute থাকলে field পূরণ না করলে form submit হয় না।")
            )
        )
    )
)

val chapter4 = Chapter(
    id = "ch4", title = "Semantic HTML", description = "অর্থবহ HTML tags ব্যবহার",
    color = 0xFFFF6B6B,
    lessons = listOf(
        Lesson(
            id = "ch4_l1", chapterId = "ch4", title = "Semantic Elements কী?", xp = 30, duration = "৮ মিনিট",
            explanation = """
Semantic HTML মানে এমন tags ব্যবহার করা যেগুলোর নিজের অর্থ আছে।

📌 Non-semantic (অর্থহীন):
<div> — শুধু container
<span> — শুধু inline container

📌 Semantic (অর্থবহ):
<header> — পেজের উপরের অংশ
<nav> — navigation menu
<main> — মূল content
<section> — একটি বিভাগ
<article> — স্বাধীন content (blog post)
<aside> — পাশের content (sidebar)
<footer> — পেজের নিচের অংশ
<figure> — image/chart এর container
<figcaption> — figure এর caption
<time> — সময়/তারিখ
<address> — যোগাযোগের তথ্য
<mark> — highlighted text
<details> — expandable content
<summary> — details এর শিরোনাম

📌 কেন ব্যবহার করবো?
✅ SEO ভালো হয়
✅ Accessibility বাড়ে
✅ Code বোঝা সহজ হয়
✅ Screen reader কাজ করে
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html lang="bn">
<body>

  <header>
    <h1>আমার ব্লগ</h1>
    <nav>
      <a href="/">হোম</a>
      <a href="/about">পরিচয়</a>
      <a href="/contact">যোগাযোগ</a>
    </nav>
  </header>

  <main>
    <article>
      <h2>প্রথম পোস্ট</h2>
      <time datetime="2026-04-20">
        ২০ এপ্রিল ২০২৬
      </time>
      <p>এটি একটি blog post।</p>
    </article>

    <aside>
      <h3>সাম্প্রতিক পোস্ট</h3>
      <ul>
        <li>HTML শেখা</li>
        <li>CSS টিপস</li>
      </ul>
    </aside>
  </main>

  <footer>
    <address>
      যোগাযোগ: info@blog.com
    </address>
    <p>&copy; 2026 আমার ব্লগ</p>
  </footer>

</body>
</html>""",
            outputHtml = """<div style="background:#1e1e1e;border-radius:8px;overflow:hidden;color:white"><header style="background:#6C63FF;padding:12px"><strong>আমার ব্লগ</strong> | <a style="color:#fff" href="#">হোম</a> <a style="color:#fff" href="#">পরিচয়</a></header><main style="display:flex;padding:12px;gap:12px"><article style="flex:2"><h3 style="color:#00BCD4;margin:0 0 4px">প্রথম পোস্ট</h3><time style="color:#999;font-size:12px">২০ এপ্রিল ২০২৬</time><p style="margin-top:8px">এটি একটি blog post।</p></article><aside style="flex:1;background:#2a2a2a;padding:8px;border-radius:6px"><p style="color:#6C63FF;font-weight:bold;margin:0 0 8px">সাম্প্রতিক</p><p style="margin:4px 0;font-size:13px">• HTML শেখা</p><p style="margin:4px 0;font-size:13px">• CSS টিপস</p></aside></main><footer style="background:#111;padding:10px;text-align:center;color:#999;font-size:12px">© 2026 আমার ব্লগ</footer></div>""",
            quizzes = listOf(
                Quiz("<article> কখন ব্যবহার করবো?",listOf("যেকোনো container হিসেবে","স্বাধীন content যেমন blog post","শুধু image এর জন্য","Navigation এর জন্য"),1,"<article> স্বাধীন, standalone content এর জন্য — যেমন blog post বা news।"),
                Quiz("Semantic HTML কেন ভালো?",listOf("SEO ও accessibility উন্নত হয়","Page speed বাড়ে","Image দ্রুত load হয়","CSS সহজ হয়"),0,"Semantic HTML SEO ও accessibility উন্নত করে।")
            )
        ),
        Lesson(
            id = "ch4_l2", chapterId = "ch4", title = "div ও span এর সঠিক ব্যবহার", xp = 25, duration = "৭ মিনিট",
            explanation = """
📌 <div> — Block level container:
পুরো লাইন জুড়ে থাকে।
Semantic অর্থ নেই।
CSS layout এর জন্য ব্যবহার হয়।

📌 <span> — Inline container:
Text এর ভেতরে ব্যবহার হয়।
লাইন break হয় না।
Text এর কিছু অংশ style করতে।

📌 কখন div/span ব্যবহার করবো?
যখন কোনো semantic tag মানানসই নয়।
শুধু styling বা scripting এর জন্য।

📌 Class ও ID দিয়ে target করা:
div.card { ... }
#main-header { ... }
span.highlight { ... }
            """.trimIndent(),
            codeExample = """<div class="card">
  <div class="card-header">
    <h3>Product Card</h3>
  </div>
  <div class="card-body">
    <p>মূল্য: 
      <span class="price">৳ ৫০০</span>
    </p>
    <p>স্টক: 
      <span style="color:green">
        পাওয়া যাচ্ছে
      </span>
    </p>
  </div>
</div>

<style>
.card {
  background: #1e1e1e;
  border-radius: 12px;
  padding: 16px;
  border: 1px solid #333;
}
.price {
  color: #FFD700;
  font-weight: bold;
  font-size: 1.2em;
}
</style>""",
            outputHtml = """<div style="background:#1e1e1e;border-radius:12px;padding:16px;border:1px solid #333;color:white"><h3 style="color:#6C63FF;margin:0 0 12px">Product Card</h3><p>মূল্য: <span style="color:#FFD700;font-weight:bold;font-size:1.2em">৳ ৫০০</span></p><p>স্টক: <span style="color:#4CAF50">● পাওয়া যাচ্ছে</span></p></div>""",
            quizzes = listOf(
                Quiz("<div> এবং <span> এর মূল পার্থক্য?",listOf("কোনো পার্থক্য নেই","div block, span inline","span block, div inline","দুটোই block"),1,"<div> block element — পুরো লাইন নেয়। <span> inline — text এর মাঝে থাকে।"),
                Quiz("Text এর একটি শব্দ highlight করতে কোনটি?",listOf("<div>","<section>","<span>","<p>"),2,"<span> inline element — text এর ভেতরে একটি অংশ style করতে ব্যবহার হয়।")
            )
        )
    )
)

val chapter5 = Chapter(
    id = "ch5", title = "HTML5 Features", description = "Modern HTML5 এর নতুন সুবিধা",
    color = 0xFFFF9800,
    lessons = listOf(
        Lesson(
            id = "ch5_l1", chapterId = "ch5", title = "Audio ও Video", xp = 35, duration = "১০ মিনিট",
            explanation = """
HTML5 দিয়ে সরাসরি audio ও video দেখানো যায়।

📌 <video> tag:
src — video file এর path
controls — play/pause/volume control দেখায়
autoplay — automatically শুরু হয়
loop — বারবার চলে
muted — sound বন্ধ
poster — thumbnail image
width, height — সাইজ

📌 <audio> tag:
controls — player দেখায়
autoplay — auto শুরু
loop — বারবার চলে

📌 Multiple Sources:
<source> tag দিয়ে একাধিক format দেওয়া যায়।
Browser যেটা support করে সেটা play করে।

📌 Supported Formats:
Video: MP4, WebM, OGG
Audio: MP3, WAV, OGG
            """.trimIndent(),
            codeExample = """<!-- Video Player -->
<video width="100%" 
       controls 
       poster="thumbnail.jpg">
  <source src="movie.mp4" 
          type="video/mp4">
  <source src="movie.webm" 
          type="video/webm">
  <p>আপনার browser video 
     support করে না।</p>
</video>

<!-- Audio Player -->
<audio controls>
  <source src="song.mp3" 
          type="audio/mpeg">
  <source src="song.ogg" 
          type="audio/ogg">
  আপনার browser audio 
  support করে না।
</audio>

<!-- YouTube embed -->
<iframe width="100%" 
        height="315"
        src="https://youtube.com/embed/VIDEO_ID"
        allowfullscreen>
</iframe>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><div style="background:#2a2a2a;border-radius:8px;padding:20px;text-align:center;margin-bottom:12px"><div style="width:60px;height:60px;background:#6C63FF;border-radius:50%;margin:0 auto 8px;display:flex;align-items:center;justify-content:center;font-size:24px">▶</div><p style="color:#999;font-size:13px">Video Player (MP4, WebM সাপোর্ট)</p></div><div style="background:#2a2a2a;border-radius:8px;padding:16px;text-align:center"><div style="display:flex;align-items:center;justify-content:center;gap:8px"><span style="color:#4CAF50">♪</span><div style="flex:1;height:4px;background:#444;border-radius:2px"><div style="width:30%;height:100%;background:#6C63FF;border-radius:2px"></div></div><span style="color:#999;font-size:12px">1:23</span></div><p style="color:#999;font-size:12px;margin-top:8px">Audio Player</p></div></div>""",
            quizzes = listOf(
                Quiz("Video controls attribute কী করে?",listOf("Video automatically চালু করে","Play/pause/volume control দেখায়","Video loop করে","Video mute করে"),1,"controls attribute browser এর built-in video player controls দেখায়।"),
                Quiz("একাধিক video format দিতে কোন tag?",listOf("<video>","<source>","<media>","<format>"),1,"<source> tag দিয়ে একাধিক format দেওয়া যায়।")
            )
        ),
        Lesson(
            id = "ch5_l2", chapterId = "ch5", title = "Canvas — Drawing API", xp = 45, duration = "১৫ মিনিট",
            explanation = """
<canvas> হলো HTML5 এর একটি drawing surface।
JavaScript দিয়ে এতে graphics আঁকা যায়।

📌 Canvas setup:
<canvas id="myCanvas" width="400" height="300">

📌 2D Context নেওয়া:
const ctx = canvas.getContext('2d');

📌 Drawing Methods:
fillRect(x,y,w,h) — ভরা rectangle
strokeRect(x,y,w,h) — border rectangle
clearRect(x,y,w,h) — clear করা
beginPath() — নতুন path শুরু
moveTo(x,y) — pen তুলে রাখা
lineTo(x,y) — line আঁকা
arc(x,y,r,start,end) — circle
fill() — ভরা shape
stroke() — border

📌 Colors:
fillStyle — ভরার color
strokeStyle — border color
lineWidth — রেখার মোটা

📌 Text:
fillText("text", x, y)
font = "20px Arial"
            """.trimIndent(),
            codeExample = """<canvas id="demo" 
        width="300" 
        height="200"
        style="background:#1e1e1e;
               border-radius:8px">
</canvas>

<script>
const canvas = 
  document.getElementById('demo');
const ctx = canvas.getContext('2d');

// Rectangle
ctx.fillStyle = '#6C63FF';
ctx.fillRect(20, 20, 100, 60);

// Circle
ctx.beginPath();
ctx.fillStyle = '#00BCD4';
ctx.arc(200, 100, 40, 0, 
        Math.PI * 2);
ctx.fill();

// Line
ctx.strokeStyle = '#FFD700';
ctx.lineWidth = 3;
ctx.beginPath();
ctx.moveTo(20, 150);
ctx.lineTo(280, 150);
ctx.stroke();

// Text
ctx.fillStyle = 'white';
ctx.font = '16px Arial';
ctx.fillText('Canvas!', 120, 180);
</script>""",
            outputHtml = """<canvas id="htmlLearnCanvas" width="300" height="200" style="background:#1e1e1e;border-radius:8px;display:block"></canvas><script>try{const c=document.getElementById('htmlLearnCanvas');const x=c.getContext('2d');x.fillStyle='#6C63FF';x.fillRect(20,20,100,60);x.beginPath();x.fillStyle='#00BCD4';x.arc(200,100,40,0,Math.PI*2);x.fill();x.strokeStyle='#FFD700';x.lineWidth=3;x.beginPath();x.moveTo(20,150);x.lineTo(280,150);x.stroke();x.fillStyle='white';x.font='16px Arial';x.fillText('Canvas!',110,185);}catch(e){}</script>""",
            quizzes = listOf(
                Quiz("Canvas এ 2D context কীভাবে নেওয়া হয়?",listOf("getContext('canvas')","getContext('2d')","getContext('draw')","getDrawing()"),1,"canvas.getContext('2d') দিয়ে 2D drawing context নেওয়া হয়।"),
                Quiz("Canvas এ circle আঁকতে কোন method?",listOf("circle()","drawCircle()","arc()","round()"),2,"ctx.arc(x,y,radius,startAngle,endAngle) দিয়ে circle/arc আঁকা হয়।")
            )
        ),
        Lesson(
            id = "ch5_l3", chapterId = "ch5", title = "LocalStorage ও SessionStorage", xp = 40, duration = "১২ মিনিট",
            explanation = """
Browser এ data সংরক্ষণের জন্য Web Storage API।

📌 LocalStorage:
Data permanently রাখে — browser বন্ধ করলেও থাকে।
একই domain এর সব page দেখতে পারে।
Max ~5MB।

📌 SessionStorage:
Tab বন্ধ করলে data মুছে যায়।
শুধু same tab এ access।

📌 Methods (উভয়ের জন্য):
setItem("key", "value") — সংরক্ষণ
getItem("key") — পড়া
removeItem("key") — মোছা
clear() — সব মোছা
length — কতটি item

📌 Object সংরক্ষণ:
String হিসেবে রাখতে হয়।
JSON.stringify() দিয়ে convert করতে হয়।
পড়তে JSON.parse() ব্যবহার।

📌 Cookies এর সাথে পার্থক্য:
Storage বড় (5MB vs 4KB)।
Server এ পাঠায় না।
            """.trimIndent(),
            codeExample = """<script>
// String সংরক্ষণ
localStorage.setItem('name', 'রাহেলা');
const name = localStorage.getItem('name');
console.log(name); // রাহেলা

// Object সংরক্ষণ
const user = {
  name: 'করিম',
  age: 18,
  level: 5
};
localStorage.setItem(
  'user', 
  JSON.stringify(user)
);

// Object পড়া
const saved = localStorage.getItem('user');
const parsed = JSON.parse(saved);
console.log(parsed.name); // করিম

// SessionStorage (same API)
sessionStorage.setItem('temp', 'value');

// সব data মোছা
// localStorage.clear();
</script>

<div id="output"></div>
<script>
  const n = localStorage.getItem('name') 
            || 'অতিথি';
  document.getElementById('output')
    .innerHTML = 'স্বাগতম, ' + n + '!';
</script>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><p style="color:#6C63FF;font-weight:bold">LocalStorage উদাহরণ:</p><div style="background:#2a2a2a;padding:12px;border-radius:6px;font-family:monospace;font-size:13px"><p style="color:#4CAF50">// Data সংরক্ষণ</p><p>localStorage.setItem('name', 'রাহেলা')</p><br><p style="color:#4CAF50">// Data পড়া</p><p>localStorage.getItem('name') → "রাহেলা"</p></div></div>""",
            quizzes = listOf(
                Quiz("LocalStorage ও SessionStorage এর পার্থক্য?",listOf("কোনো পার্থক্য নেই","Local permanent, Session tab বন্ধে মোছে","Session permanent, Local মোছে","দুটোই server এ যায়"),1,"LocalStorage permanent, SessionStorage tab বন্ধে মুছে যায়।"),
                Quiz("Object localStorage এ রাখতে কী করতে হবে?",listOf("সরাসরি রাখা যাবে","JSON.stringify() করে string বানাতে হবে","Array বানাতে হবে","কখনো রাখা যাবে না"),1,"localStorage শুধু string রাখে। Object রাখতে JSON.stringify() করতে হয়।")
            )
        )
    )
)

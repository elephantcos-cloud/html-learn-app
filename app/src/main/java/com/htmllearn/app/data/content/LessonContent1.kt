package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.*

val chapter1 = Chapter(
    id = "ch1", title = "HTML পরিচিতি", description = "HTML কী এবং কীভাবে কাজ করে",
    color = 0xFF6C63FF,
    lessons = listOf(
        Lesson(
            id = "ch1_l1", chapterId = "ch1", title = "HTML কী?", xp = 20, duration = "৫ মিনিট",
            explanation = """
HTML (HyperText Markup Language) হলো ওয়েবপেজ তৈরির মূল ভাষা।

🔹 HyperText — এটি এমন text যা অন্য document এর সাথে link করা যায়।
🔹 Markup Language — বিশেষ চিহ্ন (tags) দিয়ে content কে সাজানো হয়।

HTML দিয়ে আমরা browser কে বলি — কোন content কীভাবে দেখাতে হবে।
Browser সেই নির্দেশ মেনে সুন্দরভাবে page দেখায়।

HTML এর tags দুটো angular bracket দিয়ে লেখা হয়:
<tagname> এবং </tagname>

HTML একা ডিজাইন করে না — CSS দিয়ে সুন্দর করা হয়, JavaScript দিয়ে interactive করা হয়।
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html>
  <head>
    <title>আমার প্রথম পেজ</title>
  </head>
  <body>
    <h1>নমস্কার বিশ্ব!</h1>
    <p>এটি আমার প্রথম HTML পেজ।</p>
  </body>
</html>""",
            outputHtml = "<h1>নমস্কার বিশ্ব!</h1><p>এটি আমার প্রথম HTML পেজ।</p>",
            quizzes = listOf(
                Quiz("HTML এর পূর্ণরূপ কী?",
                    listOf("HyperText Markup Language","High Text Markup Language","HyperText Making Language","High Transfer Markup Language"),
                    0, "HTML = HyperText Markup Language — ওয়েবপেজ তৈরির ভাষা।"),
                Quiz("HTML tags কোন চিহ্ন দিয়ে লেখা হয়?",
                    listOf("() curly brackets","[] square brackets","<> angular brackets","// double slash"),
                    2, "HTML tags লেখা হয় <> angular brackets এর ভেতরে।"),
                Quiz("HTML এ closing tag কীভাবে লেখা হয়?",
                    listOf("<tag>","</tag>","[/tag]","{tag/}"),
                    1, "Closing tag এ forward slash (/) থাকে: </tagname>")
            )
        ),
        Lesson(
            id = "ch1_l2", chapterId = "ch1", title = "HTML Structure", xp = 25, duration = "৭ মিনিট",
            explanation = """
প্রতিটি HTML document এর একটি নির্দিষ্ট structure থাকে।

📌 DOCTYPE Declaration:
<!DOCTYPE html> — browser কে জানায় এটি HTML5 document।

📌 <html> tag:
পুরো document এর root element। lang attribute দিয়ে ভাষা বলা হয়।

📌 <head> section:
Page এর meta information রাখা হয় — যেমন title, charset, CSS link।
এই অংশ browser এ দেখায় না।

📌 <body> section:
এখানে যা লেখা হয় তাই browser এ দেখায় — text, images, buttons সব।

📌 Indentation:
কোড সাজানোর জন্য space বা tab ব্যবহার করা ভালো অভ্যাস।
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html lang="bn">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" 
          content="width=device-width">
    <title>পেজের শিরোনাম</title>
  </head>
  <body>
    <h1>এটি body তে আছে</h1>
    <p>এটি দেখা যাচ্ছে।</p>
    <!-- এটি একটি comment -->
  </body>
</html>""",
            outputHtml = "<h1>এটি body তে আছে</h1><p>এটি দেখা যাচ্ছে।</p>",
            quizzes = listOf(
                Quiz("DOCTYPE declaration কেন লেখা হয়?",
                    listOf("CSS যোগ করতে","Browser কে HTML5 জানাতে","JavaScript চালাতে","Title দেখাতে"),
                    1, "<!DOCTYPE html> browser কে জানায় এটি HTML5 document।"),
                Quiz("<head> section এ কী থাকে?",
                    listOf("দৃশ্যমান content","Meta information","শুধু images","শুধু links"),
                    1, "<head> এ meta information থাকে — title, charset, CSS links।"),
                Quiz("HTML comment কীভাবে লেখা হয়?",
                    listOf("// comment","/* comment */","<!-- comment -->","# comment"),
                    2, "HTML comment: <!-- এখানে comment লেখো -->")
            )
        ),
        Lesson(
            id = "ch1_l3", chapterId = "ch1", title = "HTML Elements ও Attributes", xp = 30, duration = "৮ মিনিট",
            explanation = """
HTML Element = Opening tag + Content + Closing tag

উদাহরণ: <p>এটি একটি paragraph</p>

📌 Void Elements (Self-closing):
কিছু tag এর closing tag লাগে না:
<br> — line break
<hr> — horizontal line
<img> — image
<input> — input field

📌 Attributes:
Tag এর ভেতরে extra information দেওয়া হয় attribute দিয়ে।

Format: attribute="value"

সাধারণ attributes:
• id="unique_name" — একটি unique পরিচয়
• class="style_name" — CSS style যোগ করতে
• href="url" — link এর address
• src="path" — image/file এর path
• alt="text" — image না দেখালে যা দেখাবে
• style="css" — সরাসরি CSS
            """.trimIndent(),
            codeExample = """<!-- Attributes এর উদাহরণ -->
<a href="https://google.com" 
   target="_blank">
  Google এ যাও
</a>

<img src="photo.jpg" 
     alt="আমার ছবি"
     width="200">

<p id="intro" 
   class="highlight">
  বিশেষ paragraph
</p>

<input type="text" 
       placeholder="নাম লিখুন">""",
            outputHtml = """<a href="https://google.com" target="_blank" style="color:#6C63FF">Google এ যাও</a><br><br><p style="background:#1e1e1e;padding:10px;border-radius:8px">বিশেষ paragraph</p><input type="text" placeholder="নাম লিখুন" style="padding:8px;border-radius:4px;border:1px solid #555;background:#2a2a2a;color:white">""",
            quizzes = listOf(
                Quiz("Self-closing tag কোনটি?",
                    listOf("<p>","<div>","<img>","<span>"),
                    2, "<img> একটি void element — এর closing tag লাগে না।"),
                Quiz("id attribute কী কাজ করে?",
                    listOf("Style যোগ করে","Element কে unique পরিচয় দেয়","Link তৈরি করে","Image দেখায়"),
                    1, "id attribute element কে unique পরিচয় দেয়।"),
                Quiz("alt attribute কী কাজ করে?",
                    listOf("Image resize করে","Image না দেখালে text দেখায়","Image blur করে","Image delete করে"),
                    1, "alt attribute image না দেখালে alternative text দেখায়।")
            )
        ),
        Lesson(
            id = "ch1_l4", chapterId = "ch1", title = "Browser কীভাবে HTML দেখায়", xp = 20, duration = "৫ মিনিট",
            explanation = """
Browser HTML file পড়ে একটি বিশেষ structure তৈরি করে — DOM (Document Object Model)।

📌 DOM কী?
DOM হলো HTML document এর একটি tree structure।
Browser এই tree ব্যবহার করে page render করে।

📌 Rendering Process:
1️⃣ HTML parse → DOM tree তৈরি
2️⃣ CSS parse → CSSOM তৈরি
3️⃣ DOM + CSSOM = Render Tree
4️⃣ Layout → কোথায় কী থাকবে
5️⃣ Paint → Screen এ আঁকা

📌 Browser গুলো:
• Chrome — V8 JS engine, Blink rendering
• Firefox — SpiderMonkey, Gecko
• Safari — JavaScriptCore, WebKit
• Edge — V8, Blink

📌 Developer Tools:
F12 চাপলে browser এর dev tools খোলে।
Elements tab এ HTML structure দেখা যায়।
            """.trimIndent(),
            codeExample = """<!-- Developer Tools এ inspect করো -->
<!DOCTYPE html>
<html>
  <head>
    <style>
      body { background: #0f0f0f; color: white; }
      h1   { color: #6C63FF; }
      p    { font-size: 18px; }
    </style>
  </head>
  <body>
    <h1>Browser এ দেখো</h1>
    <p>F12 চাপো এবং Elements tab দেখো।</p>
    <p>Inspect করলে DOM structure দেখবে।</p>
  </body>
</html>""",
            outputHtml = """<div style="background:#0f0f0f;padding:16px;border-radius:8px"><h1 style="color:#6C63FF">Browser এ দেখো</h1><p style="color:white">F12 চাপো এবং Elements tab দেখো।</p></div>""",
            quizzes = listOf(
                Quiz("DOM এর পূর্ণরূপ কী?",
                    listOf("Document Object Model","Data Object Mode","Document Output Method","Display Object Map"),
                    0, "DOM = Document Object Model — HTML এর tree structure।"),
                Quiz("Browser এ Developer Tools কীভাবে খোলে?",
                    listOf("Ctrl+S","F12","Alt+F4","Ctrl+Z"),
                    1, "F12 চাপলে Browser Developer Tools খোলে।")
            )
        ),
        Lesson(
            id = "ch1_l5", chapterId = "ch1", title = "প্রথম HTML ফাইল তৈরি", xp = 35, duration = "১০ মিনিট",
            explanation = """
এবার নিজে একটি HTML file তৈরি করবো!

📌 ধাপ ১ — Notepad/Editor খোলো:
Notepad, VS Code, বা যেকোনো text editor।

📌 ধাপ ২ — কোড লেখো:
নিচের কোড টাইপ করো বা copy করো।

📌 ধাপ ৩ — Save করো:
File → Save As → "index.html" নামে save করো।
File type: "All Files" বেছে নাও।

📌 ধাপ ৪ — Browser এ খোলো:
file টি double-click করো অথবা browser এ drag করো।

📌 ভালো Practice:
✅ সবসময় DOCTYPE দিয়ে শুরু করো
✅ Proper indentation রাখো
✅ Tags সঠিকভাবে close করো
✅ Meaningful নাম ব্যবহার করো
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html lang="bn">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" 
      content="width=device-width, 
               initial-scale=1.0">
    <title>আমার প্রোফাইল</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background: #0f0f0f;
        color: white;
        padding: 20px;
      }
      h1 { color: #6C63FF; }
      .card {
        background: #1e1e1e;
        padding: 20px;
        border-radius: 12px;
      }
    </style>
  </head>
  <body>
    <div class="card">
      <h1>আমার নাম</h1>
      <p>আমি HTML শিখছি!</p>
      <p>আজকের তারিখ: 
        <strong>২০ এপ্রিল ২০২৬</strong>
      </p>
    </div>
  </body>
</html>""",
            outputHtml = """<div style="background:#1e1e1e;padding:20px;border-radius:12px"><h1 style="color:#6C63FF">আমার নাম</h1><p>আমি HTML শিখছি!</p><p>আজকের তারিখ: <strong>২০ এপ্রিল ২০২৬</strong></p></div>""",
            quizzes = listOf(
                Quiz("HTML file এর extension কী হওয়া উচিত?",
                    listOf(".txt",".html",".css",".js"),
                    1, "HTML file এর extension .html হয় — যেমন index.html।"),
                Quiz("charset='UTF-8' কেন ব্যবহার করা হয়?",
                    listOf("Page speed বাড়াতে","বাংলাসহ সব ভাষা সঠিকভাবে দেখাতে","Image load করতে","Style যোগ করতে"),
                    1, "UTF-8 charset বাংলাসহ সব unicode characters সঠিকভাবে দেখায়।")
            )
        )
    )
)

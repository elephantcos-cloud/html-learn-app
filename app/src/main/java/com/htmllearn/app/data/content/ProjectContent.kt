package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.Project

val allProjects = listOf(
    Project(
        id = "p1", title = "ব্যক্তিগত প্রোফাইল পেজ", description = "নিজের পরিচিতি দিয়ে একটি সুন্দর profile page বানাও",
        difficulty = "সহজ", tags = listOf("HTML", "CSS", "Flexbox"),
        starterCode = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <title>আমার প্রোফাইল</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #0f0f0f;
      color: white;
      margin: 0;
      padding: 20px;
    }
    /* এখানে তোমার CSS যোগ করো */
  </style>
</head>
<body>
  <!-- এখানে তোমার HTML লেখো -->
  <h1>আমার নাম</h1>
  <p>আমার পরিচয়...</p>
</body>
</html>""",
        hints = listOf("header এ নাম ও photo দাও","about section এ নিজের সম্পর্কে লেখো","skills section এ তোমার দক্ষতা দেখাও","contact section এ email ও social links দাও")
    ),
    Project(
        id = "p2", title = "Restaurant Menu", description = "একটি রেস্টুরেন্টের পূর্ণ menu page তৈরি করো",
        difficulty = "মাঝারি", tags = listOf("HTML", "CSS", "Grid", "Table"),
        starterCode = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <title>রেস্টুরেন্ট মেনু</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { background: #1a0a0a; color: white; font-family: Georgia, serif; }
    header { background: #8B0000; padding: 30px; text-align: center; }
    /* আরো style যোগ করো */
  </style>
</head>
<body>
  <header>
    <h1>🍽 খানাবাড়ি</h1>
    <p>ঢাকার সেরা রেস্টুরেন্ট</p>
  </header>
  <!-- Menu sections যোগ করো -->
</body>
</html>""",
        hints = listOf("Category অনুযায়ী section ভাগ করো","প্রতিটি item এ নাম, description, দাম দাও","CSS Grid দিয়ে সুন্দর layout করো","হোভার effect যোগ করো")
    ),
    Project(
        id = "p3", title = "Quiz App", description = "JavaScript দিয়ে একটি interactive quiz তৈরি করো",
        difficulty = "কঠিন", tags = listOf("HTML", "CSS", "JavaScript"),
        starterCode = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <title>Quiz App</title>
  <style>
    body { background: #0f0f1a; color: white; font-family: Arial; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }
    .quiz-card { background: #1e1e2e; padding: 30px; border-radius: 16px; max-width: 500px; width: 90%; }
    .option { background: #2a2a3e; padding: 12px 16px; margin: 8px 0; border-radius: 8px; cursor: pointer; border: 2px solid transparent; transition: all 0.3s; }
    .option:hover { border-color: #6C63FF; }
  </style>
</head>
<body>
  <div class="quiz-card">
    <div id="progress">প্রশ্ন ১ / ৫</div>
    <h2 id="question">প্রশ্ন এখানে আসবে</h2>
    <div id="options"></div>
    <button id="next" onclick="nextQuestion()">পরের প্রশ্ন</button>
  </div>
  <script>
    const questions = [
      { q: "HTML এর পূর্ণরূপ কী?", options: ["HyperText Markup Language", "High Text Making Language", "HyperText Making Language", "High Transfer Markup Language"], correct: 0 },
      // আরো প্রশ্ন যোগ করো
    ];
    let current = 0;
    // Quiz logic এখানে লেখো
  </script>
</body>
</html>""",
        hints = listOf("Questions array তৈরি করো","Current question দেখানোর function বানাও","Option select করলে right/wrong check করো","Score দেখানোর screen যোগ করো")
    ),
    Project(
        id = "p4", title = "Landing Page", description = "একটি product বা service এর আকর্ষণীয় landing page",
        difficulty = "মাঝারি", tags = listOf("HTML", "CSS", "Animation"),
        starterCode = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Landing Page</title>
  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { background: #0f0f0f; color: white; font-family: 'Segoe UI', sans-serif; }
    nav { display: flex; justify-content: space-between; padding: 20px 40px; background: rgba(15,15,15,0.95); position: sticky; top: 0; }
    .hero { height: 100vh; display: flex; flex-direction: column; justify-content: center; align-items: center; text-align: center; background: linear-gradient(135deg, #1a1a2e, #16213e); }
    .btn { background: #6C63FF; color: white; padding: 14px 32px; border: none; border-radius: 30px; font-size: 18px; cursor: pointer; margin-top: 24px; }
  </style>
</head>
<body>
  <nav>
    <strong style="color:#6C63FF">MyProduct</strong>
    <div>
      <a href="#features" style="color:white;margin:0 12px;text-decoration:none">Features</a>
      <a href="#pricing" style="color:white;margin:0 12px;text-decoration:none">Pricing</a>
    </div>
  </nav>
  <section class="hero">
    <h1 style="font-size:3em">তোমার Product</h1>
    <p style="font-size:1.2em;color:#aaa;margin-top:16px">একটি সুন্দর tagline এখানে</p>
    <button class="btn">শুরু করো</button>
  </section>
  <!-- Features, Pricing, Footer section যোগ করো -->
</body>
</html>""",
        hints = listOf("Hero section এ compelling headline দাও","Features section এ ৩টি সুবিধা দেখাও","Pricing table যোগ করো","Footer এ contact info দাও","CSS animation দিয়ে আকর্ষণীয় করো")
    ),
    Project(
        id = "p5", title = "Portfolio Website", description = "Developer portfolio — ৫টি page সহ সম্পূর্ণ website",
        difficulty = "অনেক কঠিন", tags = listOf("HTML", "CSS", "JavaScript", "Responsive"),
        starterCode = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>আমার Portfolio</title>
  <style>
    :root {
      --primary: #6C63FF;
      --bg: #0f0f0f;
      --surface: #1e1e1e;
      --text: #ffffff;
      --text-muted: #888888;
    }
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body { background: var(--bg); color: var(--text); font-family: 'Segoe UI', sans-serif; }
    /* Navigation */
    nav { position: fixed; top: 0; width: 100%; background: rgba(15,15,15,0.9); backdrop-filter: blur(10px); padding: 16px 40px; display: flex; justify-content: space-between; align-items: center; z-index: 100; }
    nav a { color: var(--text-muted); text-decoration: none; margin-left: 24px; transition: color 0.3s; }
    nav a:hover { color: var(--primary); }
  </style>
</head>
<body>
  <!-- Sections: Home, About, Skills, Projects, Contact -->
</body>
</html>""",
        hints = listOf("৫টি section তৈরি করো: Home, About, Skills, Projects, Contact","Smooth scroll navigation যোগ করো","Skills section এ progress bar দাও","Projects section এ card layout করো","Contact form তৈরি করো","Mobile responsive করো")
    )
)

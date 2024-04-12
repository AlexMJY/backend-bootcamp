        // 점수
        let score = 0;

        

        // 배경 이미지 객체
        let bgImg1 = new Image();
        bgImg1.src = "../images/space/space.jpg";
        let bgImg2 = new Image();
        bgImg2.src = "../images/space/space.jpg";

        // 배경 좌표
        let bgY1 = 0;
        let bgY2 = -800;

        // 내 우주선 이미지 객체
        let ship1 = new Image();
        ship1.src = "../images/space/gunship4.png";
        let ship2 = new Image();
        ship2.src = "../images/space/gunship5.png";
        let ship3 = new Image();
        ship3.src = "../images/space/gunship6.png";
        let ship4 = new Image();
        ship4.src = "../images/space/gunship7.png";

        // 내 우주선 x, y 좌표
        let shipX = 275;
        let shipY = 675;

        // 미사일 이미지 객체
        let missile = new Image();
        missile.src = "../images/space/missile1.png";

        // 미사일 좌표
        let missileX = -100;
        let missileY = -100;
        // let missileX = shipX - 27.5;
        // let missileY = shipY - 40;

        // 미사일 객체 배열
        let missileArray = [];

        // 미사일 소리 객체
        let fireSound = new Audio("../sounds/fire2.wav");

        // 적 우주선 터지는 소리 객체
        let enemyScream = new Audio("../sounds/scream.wav");

        // 브금
        let bgMusic = new Audio("../sounds/Burning_Body.mp3");

        // 적 우주선 이미지 객체
        let enemyShip1 = new Image();
        enemyShip1.src = "../images/space/gunship0.png";
        let enemyShip2 = new Image();
        enemyShip2.src = "../images/space/gunship1.png";
        let enemyShip3 = new Image();
        enemyShip3.src = "../images/space/gunship2.png";
        let enemyShip4 = new Image();
        enemyShip4.src = "../images/space/gunship3.png";

        // 적 우주선 중간보스
        let enemyShip11 = new Image();
        let enemyShip12 = new Image();
        let enemyShip13 = new Image();
        let enemyShip14 = new Image();
        enemyShip11.src = "../images/space/gunship11.png";
        enemyShip12.src = "../images/space/gunship12.png";
        enemyShip13.src = "../images/space/gunship13.png";
        enemyShip14.src = "../images/space/gunship14.png";
        
        // 적 우주선 최종보스
        let enemyShip21 = new Image();
        let enemyShip22 = new Image();
        let enemyShip23 = new Image();
        let enemyShip24 = new Image();
        enemyShip21.src = "../images/space/gunship21.png";
        enemyShip22.src = "../images/space/gunship22.png";
        enemyShip23.src = "../images/space/gunship23.png";
        enemyShip24.src = "../images/space/gunship24.png";

        // 적 우주선 좌표
        let enemyShipX = 250;
        let enemyShipY = 50;

        // 적 우주선 객체 배열
        let enemyArray = [];
        
        // 전역 변수 ctx
        let ctx;

        // 전역 변수 canvas
        let canvas;

        // 게임 오버 체크
        let isGameOver = false;

        // 카운터 변수
        let counter = 0;
        
        // 윈도우 창이 로딩되면 익명함수를 실행
        window.onload = function() {
            // id가 myCanvas인 엘리먼트 객체 가져오기
            canvas = document.getElementById("myCanvas");

            // 2d Context 객체 가져오기
            ctx = canvas.getContext("2d");

            // 마우스 위치에 따라 플레이어 우주선 이동
            canvas.onmousemove = moveShip;

            // 마우스 클릭 시 미사일 발사
            canvas.onmousedown = fireMissile;

            // 게임이 중지된 상태에서
            let body = document.body;
            body.onkeydown = resetGame;

            // drawScreen();
            window.setInterval(drawScreen, 20);
        }

        function resetGame(e) {
            // console.dir(e);
            if (isGameOver) {
                if (e.keyCode == 65) {
                    isGameOver = false;
                    canvas.onmousemove = moveShip;
                    canvas.onmousedown = fireMissile;
                    shipX = 250;
                    shipY = 500;
                    score = 0;
                }
            }
        }

        function fireMissile(e) {
            let mx = shipX; // 우주선 좌측 끝에서 발사: -27.5 우측 끝: +23.5
            let my = shipY - 15;
            // missileX = e.pageX;
            // missileY = e.pageY;
            let m = {
                x : mx,
                y : my
            }

            missileArray.push(m);
            console.dir(missileArray); // 미사일 배열 출력

            // 미사일 발사 소리
            fireSound.currentSrc = 0;
            fireSound.play();
            // console.log("*미사일 발사*");

            // console.log("missile array : " + missileArray);
            
        }

        function moveShip(e) {
            // console.dir(e);
            shipX = e.pageX;
            shipY = e.pageY;
        }
        
        function makeEnemy() {
            // x좌표를 0~500 사이, y좌표 50 고정 6번 반복
            // for (let i = 0; i < 2; i++) {
            // }
            let ex = Math.floor(Math.random() * 550);
            let ey = -100;

            // 적 우주선 랜덤 생성
            // 1. 일반 우주선 (hp : 1)
            // 2. 노랑 우주선 (hp : 2)
            // 3. 파랑 우주선 (hp : 3)
            let t = Math.floor(Math.random() * 3) + 1;

            let e = {
                x : ex,
                y : ey,
                type : t,
                hp : t * 100
                
            }

            enemyArray.push(e);
            // console.log( "적우주선 만들기 : (배열상태): " + enemyArray);

        }

        function startMusic() {
            console.log("배경음악 시작");
            bgMusic.play();
        }

        function stopMusic() {
            console.log("배경음악 중지")
            bgMusic.currentTime = 0;  // 음악파일 시간 제일 처음으로 되돌리기
            bgMusic.pause;
        }

        function checkCollision() {
            // 1. 적 우주선 배열에서 적 우주선 한대 꺼내기
            for (let i = 0; i < enemyArray.length; i++) {
                let e = enemyArray[i];
                // 2. 미사일 배열에서 미사일 1개 꺼내기
                for (let j = 0; j < missileArray.length; j++) {
                    let m = missileArray[j];
                    // 3. 둘 사이의 거리를 측정해서 가까우면 폭발
                    let dis1 = pythagoras(m.x, m.y, e.x, e.y);
                    let dis2 = pythagoras(m.x+25, m.y, e.x, e.y);
                    let dis3 = pythagoras(m.x-15, m.y, e.x, e.y);
                    // 4. 맞았으면 메시지 출력
                    if (dis1 < 40 || dis2<40 || dis3<40) {
                        

                        // 맞으면 적의 체력 감소
                        e.hp -= 100;
                        e.y -= 20;
                        if (e.hp <= 0) {
                            e.y = -800;
                            score += e.type * 100;
                        } 
                        m.x = -300;
                        
                        enemyScream.currentTime=0;
                        enemyScream.play();
                    }
                    
                }
            }
        }

        function checkCollision2() {
            // 적 우주선 배열에서 한 개 꺼내기
            // 꺼낸 우주선과 내 우주선의 거리 측정
            // 가까우면 내 우주선 폭발 / 게임 오버
            for (let i = 0; i < enemyArray.length; i++) {
                let e = enemyArray[i];
                let dis = pythagoras(e.x, e.y, shipX, shipY);
                if (dis < 40) {
                    console.log("game over");
                    shipY = -200;
                    isGameOver = true;
                    gameOver();
                }
            }
        } // checkCollision2() end

        function gameOver() {
            console.log("GAME OVER");
            canvas.onmousemove = '';
            canvas.onmousedown = '';
        }

        function pythagoras(x1, y1, x2, y2) {
            return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1))) 
        }
        
        function drawScreen() {
            bgY1 += 3;
            bgY2 += 3;

            // enemyShipY += 5;

            // missileY -= 20;

            if (bgY1 >= 800) {
                bgY1 = -800;
                bgY2 = 0;
            }
            if (bgY2 >= 800) {
                bgY2 = -800;
                bgY1 = 0;
            }

            counter++;

            // 카운터 변수가 n의 배수일 때마다 적 우주선 생성
            if (counter % 30 == 0) { makeEnemy(); }
            
            // console.log("counter: " + counter);

            // 배경그리기
            ctx.drawImage(bgImg1, 0, bgY1, 600, 800);
            ctx.drawImage(bgImg2, 0, bgY2, 600, 800);

            //우주선 그리기
            if (counter % 4 == 0) { ctx.drawImage(ship1, shipX - 25, shipY - 25, 50, 50); } 
            else if (counter % 4 == 1) { ctx.drawImage(ship2, shipX - 25, shipY - 25, 50, 50); } 
            else if (counter % 4 == 2) { ctx.drawImage(ship3, shipX - 25, shipY - 25, 50, 50); } 
            else if (counter % 4 == 3) { ctx.drawImage(ship4, shipX - 25, shipY - 25, 50, 50); }

            // 적 우주선 그리기
            // if (counter % 4 == 0) { ctx.drawImage(enemyShip1, enemyShipX, enemyShipY, 50, 50); } 
            // else if (counter % 4 == 1) { ctx.drawImage(enemyShip2, enemyShipX, enemyShipY, 50, 50); } 
            // else if (counter % 4 == 2) { ctx.drawImage(enemyShip3, enemyShipX, enemyShipY, 50, 50); } 
            // else if (counter % 4 == 3) { ctx.drawImage(enemyShip4, enemyShipX, enemyShipY, 50, 50); }

            // 적 우주선 배열 그리기
            for (let i = 0; i < enemyArray.length; i++) {
                let enemy = enemyArray[i];

                if (enemy.type == 1) {
                    if (counter % 4 == 0) { ctx.drawImage(enemyShip1, enemy.x, enemy.y, 50, 50); } 
                    else if (counter % 4 == 1) { ctx.drawImage(enemyShip2, enemy.x, enemy.y, 50, 50); } 
                    else if (counter % 4 == 2) { ctx.drawImage(enemyShip3, enemy.x, enemy.y, 50, 50); } 
                    else if (counter % 4 == 3) { ctx.drawImage(enemyShip4, enemy.x, enemy.y, 50, 50); }
                } else if (enemy.type == 2) {
                    if (counter % 4 == 0) { ctx.drawImage(enemyShip11, enemy.x, enemy.y, 60, 60); } 
                    else if (counter % 4 == 1) { ctx.drawImage(enemyShip12, enemy.x, enemy.y, 60, 60); } 
                    else if (counter % 4 == 2) { ctx.drawImage(enemyShip13, enemy.x, enemy.y, 60, 60); } 
                    else if (counter % 4 == 3) { ctx.drawImage(enemyShip14, enemy.x, enemy.y, 60, 60); }
                } else if (enemy.type == 3) {
                    if (counter % 4 == 0) { ctx.drawImage(enemyShip21, enemy.x, enemy.y, 70, 70); } 
                    else if (counter % 4 == 1) { ctx.drawImage(enemyShip22, enemy.x, enemy.y, 70, 70); } 
                    else if (counter % 4 == 2) { ctx.drawImage(enemyShip23, enemy.x, enemy.y, 70, 70); } 
                    else if (counter % 4 == 3) { ctx.drawImage(enemyShip24, enemy.x, enemy.y, 70, 70); }
                }
                
                enemy.y += 3;

                // 적 우주선이 화면 밖으로 나가면 배열에서 제거
                if ( enemy.y >= 950 ) {
                    enemyArray.shift();
                }
            }

            // 미사일 그리기
            // ctx.drawImage(missile, missileX, missileY, 5, 20);
            // 미사일 배열 그리기
            for (let i = 0; i < missileArray.length; i++) {
                let m = missileArray[i];
                // 미사일 y값이 0보다 작으면 작업 X??
                m.y -= 20;
                // // 순서가 짝수면 왼쪽, 홀수면 오른쪽에서 미사일 발사
                // if ( i % 2 == 0 ) { ctx.drawImage(missile, m.x - 27.5, m.y, 5, 20); ctx.drawImage(missile, m.x, m.y, 5, 20); }
                // else if (i % 2 == 1) { ctx.drawImage(missile, m.x + 23.5, m.y, 5, 20); ctx.drawImage(missile, m.x, m.y, 5, 20); }
                ctx.drawImage(missile, m.x - 25, m.y, 5, 20); 
                ctx.drawImage(missile, m.x, m.y, 5, 20);
                ctx.drawImage(missile, m.x + 25, m.y, 5, 20);

                // 미사일이 화면 빆으로 나가면 배열에서 제거
                if (m.y <= -100) {
                    missileArray.shift();
                }
            }

            
    
            if (isGameOver) {
                ctx.fillStyle = "red";
                ctx.font="95px 고딕";
                ctx.fillText("Game Over", 50, 300);

                ctx.font="30px 고딕"
                ctx.fillText("Please Insert Coin", 150, 400);

                ctx.font="25px 고딕"
                ctx.fillText("Press 'A' key", 190, 500);
            } else {
                ctx.fillStyle = "red";
                ctx.font="35px 고딕";
                ctx.fillText("SCORE : " +  score, 350, 50);
            }
            checkCollision();
            checkCollision2();
        } // drawscreen() end
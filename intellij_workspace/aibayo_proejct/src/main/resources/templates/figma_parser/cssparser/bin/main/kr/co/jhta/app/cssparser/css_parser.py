from bs4 import BeautifulSoup
import os

def main():
    dir = "D:\\aibayo\\figma\\html\\"
    htmlname = "admin_main"
    cssname = htmlname

    try:
        # HTML 파일 읽기
        with open(os.path.join(dir, htmlname + ".html"), 'r', encoding='utf-8') as file:
            doc = BeautifulSoup(file, 'html.parser')

        # 같은 스타일을 찾기
        styles = {}
        class_counter = 1

        elements = doc.select("[style]")
        for element in elements:
            style = element.get('style')
            if style not in styles:
                class_name = f"class_{class_counter}"
                class_counter += 1
                styles[style] = class_name
            element['class'] = element.get('class', []) + [styles[style]]
            del element['style']

        # 새로운 CSS 파일 생성
        with open(os.path.join(dir, "result", cssname + ".css"), 'w', encoding='utf-8') as css_file:
            for style, class_name in styles.items():
                css_file.write(f".{class_name} {{ {style} }}\n")

        # 수정된 HTML 파일 저장
        with open(os.path.join(dir, "result", htmlname + ".html"), 'w', encoding='utf-8') as html_file:
            html_file.write(str(doc))

        print("CSS 파일과 HTML 파일이 성공적으로 생성되었습니다.")

    except IOError as e:
        print(e)

if __name__ == "__main__":
    main()

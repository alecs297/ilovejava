/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: 'jit',
    content: ['./src/main/webapp/WEB-INF/**/*.{js,jsp,html}'],
    theme: {
        extend: {
            colors: {
                "java-pink": "#FC5273",
                "java-pink-light": "#FCA5B0",
                "java-blue": "#2F76B9",
                "java-blue-light": "#7BA9D9",
                "background-default": "#FFFFFF"
            }
        },
    },
    plugins: [
        require('@tailwindcss/typography')
    ],
}

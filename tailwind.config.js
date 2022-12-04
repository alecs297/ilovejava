/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  content: ['./src/main/webapp/WEB-INF/**/*.{js,jsp,html}'],
  theme: {
    extend: {
      colors: {
        "java-pink": "#FC5273",
        "java-blue": "#2F76B9",
        "background-default": "#FFFFFF"
      }
    },
  },
  plugins: [
    require('@tailwindcss/typography')
  ],
}

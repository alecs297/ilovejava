/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: process.env. NODE_ENV ? 'jit' : undefined,
  purge: ['./src/**/*.{js,jsp,html}'],
  content: [],
  theme: {
    extend: {},
  },
  plugins: [],
}

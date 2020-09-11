const {readdirSync} = require('fs')
const docspath = '../docs/'

const files = readdirSync(docspath+process.argv[2])
console.log("---")
console.log("")
console.log("import File from '@site/src/components/File';")
console.log("")
console.log("")
files.forEach(file=>console.log(`<File filename="${process.argv[2]+"/"+file}" />`))
console.log("")
console.log("---")
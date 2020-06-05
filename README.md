```
http://localhost:8080/graphiql

mutation{
  getPdf(details:{fileId:"asddf",name:"xyz",url:"filePath"}) {
    fileId
    name
    url
  }
  }

mutation{
  getXml(details:{fileId:"asddf",name:"xyz",url:"filePath"}) {
    fileId
    name
    url
  }
  }


  
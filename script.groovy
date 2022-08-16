def buildApp() {
    echo "build application"
}

def testApp(){
    echo "test app"
    echo "${params.VERSION}"
}


return this
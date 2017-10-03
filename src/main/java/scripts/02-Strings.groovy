package scripts

def name = 'Mruczek'
def age = 5

def text = 'Ala ma kota o imieniu ${name}' // if we use '' and ${name} the whole line will be treated as string - no interpolation
def textWithInterpolation = "Ala ma kota o imieniu ${name}" // with quotes "" we will get the value of ${name}, we can also set ${1+2}
def multilineText = '''\
Ala ma kota o imieniu:
${name}
''' // will maintain the format of text , including whitespaces

// -> block of code which executs and outputs "age" variable
def multilineTextWithInterpolation = """\
Ala ma kota o imieniu:
${name}, który ma ${-> age} lat 
"""

// how to include d in e.g. postal-code
def regex = /d{2}-d{3}\d/
def regexTwo = $/d{2}-d{3}d/$

age++

println text
println textWithInterpolation
println multilineText
println multilineTextWithInterpolation
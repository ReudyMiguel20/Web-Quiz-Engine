const button = document.getElementById("btn");

const buttonQuiz = document.getElementById("btn-newQuiz");

async function fetchFunction() {
    const response = await fetch("http://localhost:8889/api/quizzes?page=0");
    const data = await response.json();
    console.log(data);
}

button.addEventListener("click", function () {
    fetchFunction();
});

buttonQuiz.addEventListener("click", function () {
    uploadQuiz()
});



const requestBody = {
    email: "test3@mail.org",
    password: "qwert"
};

function uploadQuiz() {
    fetch("http://localhost:8889/api/register", {
        method: "POST",
        body: JSON.stringify(requestBody)
    });
}

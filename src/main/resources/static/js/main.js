function change_window_to_question_details_page(id) {
    let url = window.location.pathname
    url = url + `question/get/${id}/`;
    console.log(window.location)
    window.location.pathname = url;
}



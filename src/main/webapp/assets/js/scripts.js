function confirmAction(f) {
    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима."))
    f.submit();
}

function changeLanguage(language) {
    document.cookie = "lang=" + language + "; page=/; max-age=" + (10*365*24*60*60);
    location.reload();
}

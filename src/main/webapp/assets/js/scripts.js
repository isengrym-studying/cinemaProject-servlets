function confirmAction(f) {
    if (confirm("Вы уверены, что хотите удалить выделенный пункт?\nЭта операция не восстановима."))
    f.submit();
}

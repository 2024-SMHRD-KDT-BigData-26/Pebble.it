document.addEventListener('DOMContentLoaded', function() {
  const checkboxes = document.querySelectorAll('.todo_content input[type="checkbox"]');
  checkboxes.forEach(checkbox => {
    checkbox.addEventListener('change', function() {
      if (this.checked) {
        this.nextElementSibling.classList.add('strikethrough');
      } else {
        this.nextElementSibling.classList.remove('strikethrough');
      }
    });
  });
});
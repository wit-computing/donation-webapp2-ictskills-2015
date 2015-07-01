$('#search-select')
  .dropdown();

$('.ui.checkbox')
	.checkbox();

$('.ui.form')
  .form({
      'firstName': {
      identifier  : 'firstName',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your First Name'
        }
      ]
    },
    'lastName': {
      identifier  : 'lastName',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your Last Name'
        }
      ]
    },
    'age': {
      identifier  : 'age',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your age'
        }
      ]
    },
    'state': {
      identifier  : 'state',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please select your state'
        }
      ]
    },
    'email': {
      identifier  : 'email',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your email'
        }
      ]
    },
    'password': {
      identifier : 'password',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter a password'
        },
        {
          type   : 'length[6]',
          prompt : 'Your password must be at least 6 characters'
        }
      ]
    },
    'terms': {
      identifier : 'terms',
      rules: [
        {
          type   : 'checked',
          prompt : 'You must agree to the terms and conditions'
        }
      ]
    }
  });       
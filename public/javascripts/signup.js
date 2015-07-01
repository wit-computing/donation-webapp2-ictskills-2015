$('#search-select')
  .dropdown();

$('.ui.checkbox')
	.checkbox();

$('.ui.form')
  .form({
      'user.firstName': {
      identifier  : 'user.firstName',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your First Name'
        }
      ]
    },
    'user.lastName': {
      identifier  : 'user.lastName',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your Last Name'
        }
      ]
    },
    'user.age': {
      identifier  : 'user.age',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your age'
        }
      ]
    },
    'user.state': {
      identifier  : 'user.state',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please select your state'
        }
      ]
    },
    'user.email': {
      identifier  : 'user.email',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your email'
        }
      ]
    },
    'user.password': {
      identifier : 'user.password',
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
    'user.terms': {
      identifier : 'user.terms',
      rules: [
        {
          type   : 'checked',
          prompt : 'You must agree to the terms and conditions'
        }
      ]
    }
  });       
function enable_paypal() {
    const paypal = document.getElementById('paypal_inputs');
    const card = document.getElementById('credit_card_inputs');
    // Enable paypal
    paypal.style.display = 'block';
    // Disable card
    card.style.display = 'none';
}

function enable_card() {
    const paypal = document.getElementById('paypal_inputs');
    const card = document.getElementById('credit_card_inputs');
    // Disable paypal
    paypal.style.display = 'none';
    // Enable card
    card.style.display = 'block';
}
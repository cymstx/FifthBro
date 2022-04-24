<h1 align="center"> FifthBro </h1>

FifthBro is an android app to facilitate resource pooling among clubs in SUTD. 

Currently, communication between Fifth Rows and Student Interest Groups are limited to telegram groups and email chains – this can make resource sharing a challenge.
With FifthBro, we aim to promote intra-organisational sharing by providing a hub for Fifth Row members to share and request items.


## Features

### Inventory
- View inventory of the Fifth Row you are in
- Generate QR code for lendee to scan

### Borrow
- Browse available items from other Fifth Rows
- If item is available, select desired start and end date of booking

### My Bookings
- View items booked by current user
- To check out item, tap on item to be borrowed and scan QR Code on owner's phone
- To check in item, tap on item to be returned and scan QR Code on owner's phone

## Technologies

### 1. Quick Response(QR) Code
- Each item is assigned a unique QR Code, generated based on the item's uniqueID from Firebase

### 2. Firebase
- Inventory and user information is stored in Firebase's Realtime Database -- A NoSQL database where data is stored as JSON and synchronized in realtime to every connected client. All clients share one Realtime Database instance and automatically receive updates with the newest data.
- User authentication: Firebase Authentication SDK provides methods to create and manage users and generates a unique UserID for each user instance.

## Resources

- Documentation links
  - [Firebase Realtime Database](https://firebase.google.com/docs/database)
  - [Firebase Authentication](https://firebase.google.com/docs/auth)
  - [QR Scanner](https://github.com/zxing/zxing)
  - [Unit Testing](https://developer.android.com/training/testing/local-tests)


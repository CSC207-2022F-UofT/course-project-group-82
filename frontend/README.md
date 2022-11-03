# Frontend for OuiEat

## Notion Page for this Documentation: [Here](https://www.notion.so/ari-b/Frontend-for-OuiEat-1c8cefc2edf24317adb8c9b0693cea82)


# Setting up the development Environment

1. Ensure you have Node installed on your machine (at least v16 or above)
2. Ensure you have prettier setup
3. Have a phone with Expo Go installed and logged in
4. Make sure both your phone and computer are on the same WiFi network

# Running

1. `cd` into the frontend directory
2. Run the command `npm install` inside
3. After it’s done installing, run the command `npx expo install`
4. Once that’s done you’re all setup (Hopefully without any errors)
5. You can use the command `npm run start` to start the expo session
6. The session should show up on your Expo Go app, if not, scan the QR code in the terminal

# Contributing

- Pages go inside the `src/pages/PageName`
- A page contains `components` and `assets` directories
- It also contains 4 files in the root directory:
    - A model file that stores all the data used by the page
    - A controller file that stores all the data manipulation logic
    - A view file that is what’s rendered to the user
    - An index file that exports the contents of this page from the directory
- The authentication state is managed a React context that is initialized at the root of the application.
- The root of the application is also where the routes are managed and initialized
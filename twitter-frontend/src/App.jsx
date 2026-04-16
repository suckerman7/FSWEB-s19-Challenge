import { useState } from "react";
import Login from "./components/Login";
import TweetList from "./components/TweetList";

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(
        !!localStorage.getItem("token")
    );

    return (
        <>
            {isLoggedIn ? (
                <TweetList />
            ) : (
                <Login onLogin={() => setIsLoggedIn(true)} />
            )}
        </>
    );
}

export default App;

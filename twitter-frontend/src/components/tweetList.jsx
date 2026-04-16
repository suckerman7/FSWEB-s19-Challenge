import { useState, useEffect } from "react";
import { getTweetsByUserId } from "../api/tweetApi";

export default function TweetList() {
    const [tweets, setTweets] = useState([]);
    const [error, setError] = useState("");

        console.log(localStorage.getItem("token"));

    useEffect(() => {
        const fetchTweets = async () => {
            try {
                const data = await getTweetsByUserId(1, localStorage.getItem("token"));
                setTweets(data);
            } catch (err) {
                setError(err.message);
            }
        };

        fetchTweets();
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h2>Tweets</h2>

            {error && <p style={{ color: "red" }}>{error}</p>}

            {tweets.map((tweet) => (
                <div
                    key={tweet.id}
                    style={{
                        border: "1px solid gray",
                        marginBottom: "10px",
                        padding: "10px",
                    }}
                >
                    <p>{tweet.content}</p>
                    <small>User: {tweet.username}</small>
                </div>
            ))}
        </div>
    );
}
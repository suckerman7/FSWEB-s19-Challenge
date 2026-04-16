export const getTweetsByUserId = async (userId, token) => {

    const res = await fetch(
        `http://localhost:3000/tweet/findByUserId?userId=${userId}`,
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        }
    );

    if(!res.ok) {
        throw new Error("Tweetler alınamadı!")
    }

    return await res.json();
};
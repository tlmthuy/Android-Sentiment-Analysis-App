# Sentiment Analyzer App

A smart Android application that leverages **Google Gemini AI (2.5 Flash)** to analyze the sentiment of any given text in real-time. 

## Features
- **Instant Analysis:** Enter a sentence and get a concise sentiment evaluation instantly.
- **AI Integration:** Powered by the cutting-edge Google Generative AI SDK.
- **Secure API Handling:** Uses `local.properties` and `BuildConfig` to securely manage API keys without exposing them to the repository.
- **Responsive UI:** Clean and simple user interface.

## Tech Stack
- **Language:** Java
- **AI Model:** Google Gemini-2.5-Flash (`com.google.ai.client.generativeai`)
- **Concurrency:** Guava (`ListenableFuture`, `Executor`) for smooth asynchronous API calls.
- **UI:** XML Layouts (ConstraintLayout)

## Screenshots
<img width="329" height="706" alt="image" src="https://github.com/user-attachments/assets/084e7396-ffa3-429f-be1e-31fe73c11b16" />
<img width="326" height="689" alt="image" src="https://github.com/user-attachments/assets/b11524d0-b1dd-4187-a5f7-f9e584821d67" />



## How to Run
1. Clone this repository.
2. Get your own API Key from [Google AI Studio](https://aistudio.google.com/).
3. Create a file named `local.properties` in the root directory.
4. Add your key: `API_KEY=your_api_key_here`
5. Build and run the app in Android Studio.

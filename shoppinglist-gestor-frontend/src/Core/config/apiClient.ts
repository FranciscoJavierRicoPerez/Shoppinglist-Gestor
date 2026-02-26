import axios from 'axios'

export const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL_COMPUTER,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
})
